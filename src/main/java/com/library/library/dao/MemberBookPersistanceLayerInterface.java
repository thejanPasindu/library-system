package com.library.library.dao;

import com.library.library.model.MemberBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberBookPersistanceLayerInterface extends JpaRepository<MemberBook, Long> {
    @Query("FROM MemberBook WHERE returned = false")
    List<MemberBook> findNotReturnedBooks();
}
