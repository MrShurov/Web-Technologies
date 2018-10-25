package web.web.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.web.dao.jpa.Word;

import java.util.List;

@RequestMapping("/word")
public interface WordController {

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<Word>> searchWords(String searchString);
}
