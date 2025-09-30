package com.parcial.uno.parcial_uno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;
import com.parcial.uno.parcial_uno.repository.BookRepository;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public BookDTO create(Book book) {
        try {
            Book savedBook = bookRepository.save(book);
            return toDTO(savedBook);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new BookDTO();
        }
    }

    @Override
    public BookDTO findById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::toDTO).orElse(new BookDTO());
    }

    @Override
    public BookDTO findByISBN(String isbn) {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return toDTO(book);
            }
        }
        return new BookDTO();
    }

    @Override
    public BookDTO update(String id, BookDTO bookDTO) {
        try {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                Book libro = optionalBook.get();
                libro.setIsbn(bookDTO.getIsbn());
                libro.setName(bookDTO.getName());
                libro.setAmount(bookDTO.getAmount());
                libro.setAvailable(bookDTO.getAvailable());
                Book updatedBook = bookRepository.save(libro);
                return toDTO(updatedBook);
            }
            return new BookDTO();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new BookDTO();
        }
    }

    @Override
    public String delete(String id) {
        try {
            if (bookRepository.existsById(id)) {
                bookRepository.deleteById(id);
                return "El libro fue eliminado de forma correcta";
            }
            return "El libro no fue encontrado";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private BookDTO toDTO(Book book) {
        BookDTO newBook = new BookDTO();
        newBook.setBookId(book.getBookId());
        newBook.setIsbn(book.getIsbn());
        newBook.setName(book.getName());
        newBook.setAmount(book.getAmount());
        newBook.setAvailable(book.getAvailable());
        return newBook;
    }

    /*private Book fromDTO(BookDTO book) {
        Book newBook = new Book();
        newBook.setBookId(book.getBookId());
        newBook.setIsbn(book.getIsbn());
        newBook.setName(book.getName());
        newBook.setAmount(book.getAmount());
        newBook.setAvailable(book.getAvailable());
        return newBook;
    }*/
}
