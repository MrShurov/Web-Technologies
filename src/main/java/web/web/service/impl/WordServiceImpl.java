package web.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.web.dao.api.WordDao;
import web.web.dao.jpa.Word;
import web.web.service.api.WordService;

import java.util.List;

import static web.web.service.Search.search;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordDao wordDao;

    @Override
    public List<Word> searchWords(String searchString) {
        List<Word> words = wordDao.findAll();
        return search(searchString, words);
    }
}
