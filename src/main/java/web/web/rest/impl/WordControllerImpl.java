package web.web.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.web.dao.jpa.Word;
import web.web.rest.api.WordController;
import web.web.service.api.WordService;

import java.util.List;

@RestController
public class WordControllerImpl implements WordController {

    @Autowired
    WordService wordService;

    @Override
    public ResponseEntity<List<Word>> searchWords(@RequestParam String searchString) {
        return new ResponseEntity<>(wordService.searchWords(searchString), HttpStatus.OK);
    }
}
