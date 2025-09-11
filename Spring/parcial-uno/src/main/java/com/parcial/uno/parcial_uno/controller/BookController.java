// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.parcial.uno.parcial_uno.controller;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;
import com.parcial.uno.parcial_uno.service.IBookService;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/books"})
public class BookController {
   private final IBookService bookService;

   public BookController(IBookService bookService) {
      this.bookService = bookService;
   }

   @GetMapping
   public List<BookDTO> getAllBooks() {
      return this.bookService.getAll();
   }

   @PostMapping
   public BookDTO createBook(@RequestBody Book book) {
      return this.bookService.create(book);
   }

   @GetMapping({"/{id}"})
   public ResponseEntity<BookDTO> getBookById(@PathVariable("id") String id) {
      BookDTO book = this.bookService.findById(id);
      return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
   }

   @GetMapping({"/isbn/{isbn}"})
   public ResponseEntity<BookDTO> getBookByISBN(@PathVariable("isbn") String isbn) {
      BookDTO book = this.bookService.findByISBN(isbn);
      return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
   }

   @PutMapping({"/{id}"})
   public ResponseEntity<BookDTO> updateBook(@PathVariable("id") String id, @RequestBody BookDTO bookDTO) {
      BookDTO updated = this.bookService.update(id, bookDTO);
      return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
   }

   @DeleteMapping({"/{id}"})
   public ResponseEntity<String> deleteBook(@PathVariable("id") String id) {
      String result = this.bookService.delete(id);
      return "Libro eliminado".equals(result) ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
   }

   @PostMapping({"/params"})
   public ResponseEntity<Object> getBookByParams(@RequestParam("isbn") String isbn, @RequestParam("name") String name) {
      BookDTO book = this.bookService.findByISBN(isbn);
      if (book != null && book.getName().equalsIgnoreCase(name)) {
         HashMap<String, Object> response = new HashMap();
         response.put("name", book.getName());
         response.put("amount", book.getAmount());
         response.put("available", book.getAvailable());
         return ResponseEntity.ok(response);
      } else {
         return ResponseEntity.notFound().build();
      }
   }
}
