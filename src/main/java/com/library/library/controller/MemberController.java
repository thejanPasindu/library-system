package com.library.library.controller;

import com.library.library.model.Member;
import com.library.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("new")
    public String showSignUpForm(Member member) {
        return "add-member";
    }

    @PostMapping("new")
    public String addAuther(@Valid Member member, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addAttribute("memberName", member.getMemberName());
            return "add-member";
        }
        if (memberService.saveMember(member)){
            return "redirect:/member/new";
        }
        return "/error/500";
    }
}
