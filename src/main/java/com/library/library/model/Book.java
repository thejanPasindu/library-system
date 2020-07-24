package com.library.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private long bookId;
    @NotBlank(message = "Book name can't be empty")
    @Column(name = "book_name")
    private String bookName;
    @NotBlank(message = "Book discription can't be empty")
    @Column(name = "discription")
    private String description;
    @ManyToOne
    private Author author;
    private boolean borrowed;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberBook> memberBooks = new ArrayList<MemberBook>();

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public List<MemberBook> getMemberBooks() {
        return memberBooks;
    }

    public void setMemberBooks(List<MemberBook> memberBooks) {
        this.memberBooks = memberBooks;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", borrowed=" + borrowed +
                ", memberBooks=" + memberBooks +
                '}';
    }
}
