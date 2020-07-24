package com.library.library.controller;

import com.library.library.model.Author;
import com.library.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/author/")
public class AuthorConroller {

    private final AuthorService authorService;

    @Autowired
    public AuthorConroller(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String showAddAuthorForm(Author author) {
        return "add-author";
    }

    @PostMapping("/new")
    public String addAuther(@Valid Author author, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addAttribute("name", author.getName());
            return "add-author";
        }
        if(authorService.saveAuthor(author)){
            return "redirect:/author/";
        }
        System.out.println("Here here");
        return "/error/500";
    }
}
