package web.web.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.web.dao.jpa.Site;
import web.web.rest.api.SiteController;
import web.web.service.api.SiteService;

import java.util.List;

@RestController
public class SiteControllerImpl implements SiteController {

    @Autowired
    SiteService siteService;

    @Override
    public ResponseEntity<List<Site>> getAll() {
        return new ResponseEntity<>(siteService.getAll(), HttpStatus.OK);
    }

    @Override
    public void addSite(@RequestParam String url) {
        siteService.addSite(url);
    }
}
