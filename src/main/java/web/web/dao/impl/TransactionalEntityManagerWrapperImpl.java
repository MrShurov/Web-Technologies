package web.web.dao.impl;

import org.springframework.stereotype.Component;
import web.web.dao.api.TransactionalEntityManagerWrapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * The wrapper for the entity manager.
 * Provides a single persistence context for all dependent dao instances.
 */
@Component
public class TransactionalEntityManagerWrapperImpl implements TransactionalEntityManagerWrapper {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
