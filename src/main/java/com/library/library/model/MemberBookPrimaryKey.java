package com.library.library.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class MemberBookPrimaryKey implements Serializable {
    @Column(name = "book_id")
    private long book_id;
    @Column(name = "member_id")
    private long member_id;

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }
}
