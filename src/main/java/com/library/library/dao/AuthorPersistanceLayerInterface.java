package com.library.library.dao;

import com.library.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorPersistanceLayerInterface extends JpaRepository<Author, Long> {

    @Query("FROM Author WHERE name = ?1")
    List<Author> findByFirstName(String firstName);
}
