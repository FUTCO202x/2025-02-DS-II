package com.parcial.uno.parcial_uno.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;
import com.parcial.uno.parcial_uno.service.IBookService;




@RestController
@RequestMapping("/api/books")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService=bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }
    
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable("id") String id) {
        return bookService.findById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public BookDTO getBookByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.findByISBN(isbn);
    }
    
    @PostMapping
    public BookDTO createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable("id") String id, @RequestBody BookDTO book) {
        return bookService.update(id,book);
    }
    
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") String id){
        return bookService.delete(id);
    }

}
