package web.web.dao.impl;

import org.springframework.stereotype.Repository;
import web.web.dao.api.SiteDao;
import web.web.dao.jpa.Site;

@Repository
public class SiteDaoImpl extends TransactionalDaoImpl<Site, Site> implements SiteDao {

    public SiteDaoImpl() {
        super(Site.class);
    }

    @Override
    public Site fromEntity(Site entity) {
        return entity;
    }

    @Override
    public Site toEntity(Site entity) {
        return entity;
    }
}
