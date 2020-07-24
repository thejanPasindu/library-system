package com.library.library.service;

import com.library.library.dao.BookPersistanceLayerInterface;
import com.library.library.errors.MyAccessDeniedHandler;
import com.library.library.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    private  final BookPersistanceLayerInterface bookPersistanceLayerInterface;
    private final AuthorService authorService;
    private final MemberService memberService;

    @Autowired
    public BookService(BookPersistanceLayerInterface bookPersistanceLayerInterface,
                       AuthorService authorService,
                       MemberService memberService) {
        this.bookPersistanceLayerInterface = bookPersistanceLayerInterface;
        this.authorService = authorService;
        this.memberService = memberService;
    }

    public boolean addBookToSystem(Book book){
        try {
            Author author = authorService.findById(book.getAuthor().getAuthorId());
            if(author==null){
                return false;
            }
            book.setAuthor(author);
            bookPersistanceLayerInterface.save(book);
            return true;
        }catch (Exception e){
            logger.error("Book not correctly add to the system");
            return false;
        }
    }

    public List<Book> findAvailableBooks(){
        return bookPersistanceLayerInterface.findAvailableBooks();
    }

    public Book findById(long id){
        return bookPersistanceLayerInterface.findById(id).orElse(new Book());
    }

    public boolean assignBookToMember(MemberBookTmp memberBookTmp){
        try {
            Book book = this.findById(memberBookTmp.getBookId());
            Member member = memberService.findById(memberBookTmp.getMemberId());
            MemberBook memberBook = new MemberBook();
            memberBook.setBook(book);
            memberBook.setMember(member);
            memberBook.setStartDate(new Date());
            member.setBorrowed(true);
            book.getMemberBooks().add(memberBook);
            book.setBorrowed(true);
            bookPersistanceLayerInterface.save(book);
            return true;
        }catch (Exception e){
            logger.error("Book is not assign to the member");
            return false;
        }
    }

}
