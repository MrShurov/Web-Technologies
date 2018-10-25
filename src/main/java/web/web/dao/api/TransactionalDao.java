package web.web.dao.api;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Generic dao
 *
 * @param <T> transfer object class
 * @param <E> entity class
 */
public interface TransactionalDao<T, E> {
    /**
     * find object by pk object
     *
     * @param id object id
     * @return object, if found. else throws NotFoundExceptionHelper
     */
    T findById(Object id);

    /**
     * find object by pk object
     *
     * @param id object id
     * @return Optional object
     */
    Optional<T> getById(Object id);

    /**
     * find entity by pk object
     *
     * @param id object id
     * @return Optional object
     */
    Optional<E> getEntityById(Object id);

    /**
     * find entity by pk object
     *
     * @param id object id
     * @return entity, if found. else throws NotFoundExceptionHelper
     */
    E findEntityById(Object id);

    /**
     * lists all objects
     *
     * @return list of all objects
     */
    List<T> findAll();

    /**
     * saves object
     *
     * @param entity object to save
     * @return saved object
     */
    T save(T entity);

    /**
     * saves entity
     *
     * @param entity object to save
     * @return saved object
     */
    E saveEntity(E entity);

    /**
     * saves list of objects
     *
     * @param entities list of objects to save
     * @return list of saved objects
     */
    List<T> saveAll(List<T> entities);

    /**
     * updates object
     *
     * @param entity object to update
     * @return updated object
     */
    T update(T entity);

    /**
     * updates list of objects
     *
     * @param entities list of objects to update
     * @return list of updated objects
     */
    List<T> updateAll(List<T> entities);

    /**
     * deletes object
     *
     * @param entity object to delete
     */
    void delete(T entity);

    /**
     * deletes object by id
     *
     * @param id object id
     */
    void deleteById(Object id);

    /**
     * deletes all objects
     *
     * @param entities objects to delete
     */
    void deleteAll(List<T> entities);

    /**
     * transforms transfer object to entity
     *
     * @param entity object
     * @return entity
     */
    T fromEntity(E entity);

    /**
     * transforms entity to transfer object
     *
     * @param transfer entity
     * @return object
     */
    E toEntity(T transfer);

    /**
     * transforms list of entity to list of transfer object
     *
     * @param list list of entity
     * @return list of transfer object
     */
    List<T> fromEntityList(Collection<E> list);

    /**
     * update entity
     *
     * @param entity entity
     */
    void updateEntity(E entity);
}