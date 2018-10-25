package web.web.dao.api;

import javax.persistence.EntityManager;

/**
 * The wrapper for the entity manager.
 * Provides a single persistence context for all dependent dao instances.
 */
@FunctionalInterface
public interface TransactionalEntityManagerWrapper {

    /**
     * get entity manager
     *
     * @return entity manager
     */
    EntityManager getEntityManager();
}
