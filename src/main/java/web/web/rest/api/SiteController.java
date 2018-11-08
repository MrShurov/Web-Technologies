package web.web.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.web.dao.jpa.Site;

import java.util.List;

@RequestMapping("rest/site")
public interface SiteController {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Site>> getAll();

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public void addSite(String url);
}
