package com.library.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import javafx.print.Collation;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private long authorId;
    @NotBlank(message = "Author name can't be null")
    private String name;
    @OneToMany(mappedBy = "author")
    private Collection<Book> books =  new ArrayList<Book>();

    public Author() {
        super();
    }

    public Author(long authorId, String authorName) {
        super();
        this.authorId = authorId;
        this.name = authorName;
    }

    public Author(String authorName) {
        super();
        this.name = authorName;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
