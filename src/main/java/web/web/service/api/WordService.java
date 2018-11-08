package web.web.service.api;

import web.web.dao.jpa.Word;
import web.web.rest.to.SearchTO;

import java.util.List;

public interface WordService {

    public List<SearchTO> searchWords(String searchString);
}
