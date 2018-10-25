package web.web.dao.impl;

import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import web.web.dao.api.TransactionalDao;
import web.web.dao.api.TransactionalEntityManagerWrapper;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Generic dao implementation
 *
 * @param <T> transfer object class
 * @param <E> entity class
 */
public abstract class TransactionalDaoImpl<T, E> implements TransactionalDao<T, E> {

  private static final Logger LOGGER = LogManager.getLogger(TransactionalDaoImpl.class);

  @Autowired
  private TransactionalEntityManagerWrapper emWrapper;

  private final Class<E> entityType;

  private static final String FIND_ALL_FORMAT = "select e from %s e";


  /**
   * creates TransactionalDaoImpl for entity
   *
   * @param entityType class of entity
   */
  public TransactionalDaoImpl(final Class<E> entityType) {
    this.entityType = entityType;
  }

  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#findById(java.lang.Object)
   */
  @Override
  public T findById(final Object id) {
    E result = getEntityManager().find(entityType, id);
    try{
      if (result == null) {
        throw new NotFoundException("Object not found with id " + id);
      }
    } catch (NotFoundException e){
      LOGGER.error("Object not found with id " + id);
    }
    return fromEntity(result);
  }

  @Override
  public Optional<T> getById(Object id) {
    E result = getEntityManager().find(entityType, id);
    return result == null ? Optional.empty() : Optional.of(fromEntity(result));
  }

  @Override
  public Optional<E> getEntityById(Object id) {
    E result = getEntityManager().find(entityType, id);
    return result == null ? Optional.empty() : Optional.of(result);
  }

  @Override
  public E findEntityById(final Object id) {
    E result = getEntityManager().find(entityType, id);
    try{
      if (result == null) {
        throw new NotFoundException("Object not found with id " + id);
      }
    } catch (NotFoundException e){
      LOGGER.error("Object not found with id " + id);
    }
    return result;
  }


  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#findAll()
   */
  @Override
  public List<T> findAll() {
    String query = String.format(FIND_ALL_FORMAT, entityType.getSimpleName());
    return getEntityManager().createQuery(query, entityType).getResultList().stream()
        .map(this::fromEntity).collect(Collectors.toList());
  }

  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#save(T)
   */
  @Override
  public T save(final T entity) {
    E en = toEntity(entity);
    getEntityManager().persist(en);
    return fromEntity(en);
  }

  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#saveEntity(T)
   */
  @Override
  public E saveEntity(final E entity) {
    getEntityManager().persist(entity);
    return entity;
  }

  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#saveAll(java.util.List)
   */
  @Override
  public List<T> saveAll(final List<T> entities) {
    List<T> res = new ArrayList<>();
    if (!CollectionUtils.isEmpty(entities)) {
      for (T entity : entities) {
        E en = toEntity(entity);
        getEntityManager().persist(en);
        res.add(fromEntity(en));
      }
    }
    return res;
  }

  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#update(T)
   */
  @Override
  public T update(final T entity) {
    return fromEntity(getEntityManager().merge(toEntity(entity)));
  }

  @Override
  public void updateEntity(E entity) {
    getEntityManager().merge(entity);
  }

  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#updateAll(java.util.List)
   */
  @Override
  public List<T> updateAll(final List<T> entities) {
    List<T> res = new ArrayList<>();
    if (!CollectionUtils.isEmpty(entities)) {
      for (T entity : entities) {
        res.add(fromEntity(getEntityManager().merge(toEntity(entity))));
      }
    }

    return res;
  }

  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#delete(T)
   */
  @Override
  public void delete(final T entity) {
    E en = toEntity(entity);
    en = getEntityManager().merge(en);
    getEntityManager().remove(en);
  }

  @Override
  public void deleteById(final Object id) {
    getEntityManager().remove(getEntityManager().find(entityType, id));
  }

  /* (non-Javadoc)
   * @see int_.wipo.ipcrms.dao.impl.TransactionDao#deleteAll(java.util.List)
   */
  @Override
  public void deleteAll(final List<T> entities) {
    for (T entity : entities) {
      E en = getEntityManager().merge(toEntity(entity));
      getEntityManager().remove(en);
    }
  }

  protected EntityManager getEntityManager() {
    return emWrapper.getEntityManager();
  }

  @Override
  public List<T> fromEntityList(Collection<E> list) {
    return list.stream().map(this::fromEntity).collect(Collectors.toList());
  }

  public void setEmWrapper(TransactionalEntityManagerWrapper emWrapper) {
    this.emWrapper = emWrapper;
  }
}
