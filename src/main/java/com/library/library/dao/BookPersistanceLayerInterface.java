package com.library.library.dao;

import com.library.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookPersistanceLayerInterface extends JpaRepository<Book, Long> {

    @Query("FROM Book WHERE borrowed = false")
    List<Book> findAvailableBooks();

    @Query("FROM Book WHERE borrowed = true")
    List<Book> findReturnBooks();
}
