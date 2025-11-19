package com.parcial.uno.parcial_uno.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.parcial.uno.parcial_uno.model.Book;
import com.parcial.uno.parcial_uno.service.IBookService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService=bookService;
    }

    //cambiar Book por BookDTO
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }
    

}
