package com.library.library.model;

public class MemberBookTmp {
    private long memberId;
    private long bookId;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "MemberBookTmp{" +
                "memberId=" + memberId +
                ", bookId=" + bookId +
                '}';
    }
}
