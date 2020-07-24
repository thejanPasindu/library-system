package com.library.library.service;

import com.library.library.dao.AuthorPersistanceLayerInterface;
import com.library.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorPersistanceLayerInterface authorPersistanceLayerInterface;

    @Autowired
    public AuthorService(AuthorPersistanceLayerInterface authorPersistanceLayerInterface) {
        this.authorPersistanceLayerInterface = authorPersistanceLayerInterface;
    }

    public List<Author> findAllAuthors(){
        return authorPersistanceLayerInterface.findAll();
    }

    public Author findById(long id){
        return authorPersistanceLayerInterface.findById(id).orElse(new Author());
    }

    public boolean saveAuthor(Author author){
        try {
            authorPersistanceLayerInterface.save(author);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
