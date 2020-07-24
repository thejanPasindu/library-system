package com.library.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long memberId;
    @NotBlank(message = "Name Can't be Null")
    @Column(name = "member_name")
    private String memberName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberBook> books = new ArrayList<MemberBook>();

    private boolean borrowed;

    public Member() {
        super();
    }

    public Member(Long memberId, String memberName) {
        super();
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public Member(String memberName) {
        super();
        this.memberName = memberName;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<MemberBook> getBooks() {
        return books;
    }

    public void setBooks(List<MemberBook> books) {
        this.books = books;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", books=" + books +
                ", borrowed=" + borrowed +
                '}';
    }
}
