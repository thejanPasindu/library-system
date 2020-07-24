package com.library.library.dao;

import com.library.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPersistanceLayerInterface extends JpaRepository<Member, Long> {

    @Query("FROM Member WHERE borrowed=false")
    List<Member> findAvailableMembers();
}
