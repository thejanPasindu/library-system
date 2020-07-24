package com.library.library.service;


import com.library.library.dao.MemberPersistanceLayerInterface;
import com.library.library.errors.MyAccessDeniedHandler;
import com.library.library.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);
    private final MemberPersistanceLayerInterface memberPersistanceLayerInterface;

    @Autowired
    public MemberService(MemberPersistanceLayerInterface memberPersistanceLayerInterface) {
        this.memberPersistanceLayerInterface = memberPersistanceLayerInterface;
    }

    public List<Member> findAvailableMembers(){
        return memberPersistanceLayerInterface.findAvailableMembers();
    }

    public Member findById(long id){
        return memberPersistanceLayerInterface.findById(id).orElse( new Member());
    }

    public boolean saveMember(Member member){
        try {
            memberPersistanceLayerInterface.save(member);
            return true;
        }catch (Exception exception){
            logger.error("Member Not Saved");
            return false;
        }
    }
}
