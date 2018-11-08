package web.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.web.dao.api.WordDao;
import web.web.dao.jpa.Word;
import web.web.rest.to.SearchTO;
import web.web.service.api.WordService;

import javax.management.ObjectName;
import java.util.*;

import static web.web.service.Search.search;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordDao wordDao;

    @Override
    public List<SearchTO> searchWords(String searchString) {
        List<Word> words = wordDao.findAll();
        List<Word> searchWords = search(searchString, words);
        List<Word> wordList = new ArrayList<>();
        searchWords.forEach(word ->  wordList.add(new Word(word.getWord(),word.getSite())));
        Set<Word> set = new HashSet<>(wordList);
        List<SearchTO> result = new ArrayList<>();
        for (Word aSet : set) {
            result.add(new SearchTO(aSet.getWord(), 0, aSet.getSite().getUrl()));
        }
        for (SearchTO aResult : result) {
            for (Word aWords : searchWords) {
                if (aResult.getWord().equals(aWords.getWord()) && aResult.getUrl().equals(aWords.getSite().getUrl())) {
                    aResult.setCount(aResult.getCount() + 1);
                }
            }
        }
        result.sort((a, b) -> b.getCount().compareTo(a.getCount()));
        return result;
    }
}
