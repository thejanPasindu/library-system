package com.library.library.controller;

import com.library.library.model.*;
import com.library.library.service.AuthorService;
import com.library.library.service.BookService;
import com.library.library.service.MemberBookService;
import com.library.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/book/")
public class BookController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final MemberService memberService;
    private final MemberBookService memberBookService;

    @Autowired
    public BookController(AuthorService authorService,
                          BookService bookService,
                          MemberService memberService,
                          MemberBookService memberBookService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.memberService = memberService;
        this.memberBookService = memberBookService;
    }

    @GetMapping("new")
    public String showAddBookForm(Book book, Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "add-book";
    }

    @PostMapping("new")
    public String addBookToSystem(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bookName", book.getBookName());
            model.addAttribute("description", book.getDescription());
            model.addAttribute("authors", authorService.findAllAuthors());
            return "add-book";
        }
        if (bookService.addBookToSystem(book)){
            return "redirect:/book/new";
        }
        return "/error/500";
    }

    @GetMapping
    public String getAvailableBooks(Model model){
        model.addAttribute("availableBooks", bookService.findAvailableBooks());
        return "available-books";
    }

    @GetMapping("/{id}")
    public String assignBookToMember(@PathVariable long id, MemberBookTmp memberBookTmp, Model model){
        Book book = bookService.findById(id);
        if(book == null){
            return "/error/404";
        }
        model.addAttribute("book", book);
        model.addAttribute("members", memberService.findAvailableMembers());
        return "assign-book";
    }

    @PostMapping
    public String assignBookToMember(MemberBookTmp memberBookTmp, BindingResult result) {
        if (result.hasErrors()) {
            return "/error/404";
        }
        System.out.println();
        if(bookService.assignBookToMember(memberBookTmp)){
            return "redirect:/book/";
        }
        return "/error/404";
    }

    @GetMapping("return")
    public String getReturnBooks(Model model){
        model.addAttribute("return_books", memberBookService.findNotReturnedBooks());
        return "return-book-list";
    }

    @GetMapping("return/{id}")
    public RedirectView returnBookFromMember(@PathVariable long id){
        if(memberBookService.returnBookFromMember(id)){
            return new RedirectView("/book/");
        }
        return new RedirectView(" /error/500");
    }
}
