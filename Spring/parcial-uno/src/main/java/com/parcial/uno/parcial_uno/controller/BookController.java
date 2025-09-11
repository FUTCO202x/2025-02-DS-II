package com.parcial.uno.parcial_uno.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;
import com.parcial.uno.parcial_uno.service.IBookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    // 1. Obtener todos los libros
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAll();
    }

    // 2. Crear libro
    @PostMapping
    public BookDTO createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    // 3. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") String id) {
        BookDTO book = bookService.findById(id);
        return (book != null) ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    // 4. Buscar por ISBN
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByISBN(@PathVariable("isbn") String isbn) {
        BookDTO book = bookService.findByISBN(isbn);
        return (book != null) ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    // 5. Actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") String id, @RequestBody BookDTO bookDTO) {
        BookDTO updated = bookService.update(id, bookDTO);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // 6. Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id) {
        String result = bookService.delete(id);
        if ("Libro eliminado".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/params")
    public ResponseEntity<Object> getBookByParams(
            @RequestParam("isbn") String isbn,
            @RequestParam("name") String name) {

        BookDTO book = bookService.findByISBN(isbn);

        if (book != null && book.getName().equalsIgnoreCase(name)) {
            var response = new java.util.HashMap<String, Object>();
            response.put("name", book.getName());
            response.put("amount", book.getAmount());
            response.put("available", book.getAvailable());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    


}
