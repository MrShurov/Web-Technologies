package web.web.service.api;

import web.web.controller.to.SearchTO;

import java.util.List;

public interface WordService {

    public List<SearchTO> searchWords(String searchString);
}
