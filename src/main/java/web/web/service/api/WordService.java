package web.web.service.api;

import web.web.dao.jpa.Word;

import java.util.List;

public interface WordService {

    public List<Word> searchWords(String searchString);
}
