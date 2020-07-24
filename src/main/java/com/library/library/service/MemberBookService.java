package com.library.library.service;

import com.library.library.dao.MemberBookPersistanceLayerInterface;
import com.library.library.errors.MyAccessDeniedHandler;
import com.library.library.model.MemberBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberBookService {

    private static final Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);
    private final MemberBookPersistanceLayerInterface memberBookPersistanceLayerInterface;

    @Autowired
    public MemberBookService(MemberBookPersistanceLayerInterface memberBookPersistanceLayerInterface) {
        this.memberBookPersistanceLayerInterface = memberBookPersistanceLayerInterface;
    }

    public List<MemberBook> findNotReturnedBooks(){
        return memberBookPersistanceLayerInterface.findNotReturnedBooks();
    }

    public MemberBook findById(long id){
        return memberBookPersistanceLayerInterface.findById(id).orElse(new MemberBook());
    }

    public boolean returnBookFromMember(long id){
        try {
            MemberBook memberBook = this.findById(id);
            memberBook.getMember().setBorrowed(false);
            memberBook.getBook().setBorrowed(false);
            memberBook.setReturned(true);
            memberBook.setReturnDate(new Date());
            memberBookPersistanceLayerInterface.save(memberBook);
            return true;
        }catch (Exception e){
            logger.error("Member and Book Data not save in return");
            return false;
        }

    }
}
