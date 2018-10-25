package web.web.service.api;

import web.web.dao.jpa.Site;

import java.util.List;

public interface SiteService {

    public Site addSite(String url);

    public List<Site> getAll();
}
