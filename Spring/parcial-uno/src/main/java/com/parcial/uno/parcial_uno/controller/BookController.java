package com.parcial.uno.parcial_uno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;
import com.parcial.uno.parcial_uno.service.IBookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    // Obtener todos los libros (GET)
    // http://localhost:9000/books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    // Crear un nuevo libro (POST)
    // http://localhost:9000/books
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody Book book) {
        if (book == null || book.getBookId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        BookDTO created = bookService.create(book);
        return ResponseEntity.ok(created);
    }

    // Buscar libro por ID (GET)
    // http://localhost:9000/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String id) {
        BookDTO book = bookService.findById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    // Buscar libro por ISBN (GET)
    // http://localhost:9000/books/isbn/{isbn}
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByISBN(@PathVariable String isbn) {
        BookDTO book = bookService.findByISBN(isbn);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    // Actualizar libro por ID (PUT)
    // http://localhost:9000/books/{id}
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String id, @RequestBody BookDTO bookDTO) {
        BookDTO updated = bookService.update(id, bookDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // Eliminar libro por ID (DELETE)
    // http://localhost:9000/books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        String result = bookService.delete(id);
        if ("Libro no encontrado".equals(result)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
