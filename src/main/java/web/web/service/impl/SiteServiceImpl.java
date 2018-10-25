package web.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.web.dao.api.SiteDao;
import web.web.dao.api.WordDao;
import web.web.dao.jpa.Site;
import web.web.dao.jpa.Word;
import web.web.service.api.SiteService;

import java.util.List;

import static web.web.service.TextParser.parseSite;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    SiteDao siteDao;
    @Autowired
    WordDao wordDao;

    @Override
    @Transactional
    public Site addSite(String url) {
        Site site = new Site(url);
        site = siteDao.save(site);
        List<Word> words = parseSite(site);
        wordDao.saveAll(words);
        return site;
    }

    @Override
    public List<Site> getAll() {
        return siteDao.findAll();
    }
}
