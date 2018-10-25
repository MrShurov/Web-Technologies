package web.web.dao.impl;

import org.springframework.stereotype.Repository;
import web.web.dao.api.WordDao;
import web.web.dao.jpa.Word;

@Repository
public class WordDaoImpl extends TransactionalDaoImpl<Word, Word> implements WordDao {

    public WordDaoImpl() {
        super(Word.class);
    }

    @Override
    public Word fromEntity(Word entity) {
        return entity;
    }

    @Override
    public Word toEntity(Word entity) {
        return entity;
    }
}
