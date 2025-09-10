package com.parcial.uno.parcial_uno.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;

@Service
public class BookService implements IBookService {

    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book("6600ab76-3", "0002005018", "Clara Callan", 5, true));
        books.add(new Book("297c17d8-4", "0195153448", "Classical Mythology", 3, true));
        books.add(new Book("11b553eb-b", "0399135782", "The kitchen God's wife", 8, true));
        books.add(new Book("3c24c2fa-3", "0440234743", "The testament", 4, true));
        books.add(new Book("eb25c2d4-7", "0393045218", "The mummies of Urumchi", 5, true));
        books.add(new Book("1940136a-2", "0060973129", "Decision in Normandy", 3, true));
        books.add(new Book("12a13228-0", "0345402871", "Airframe", 1, true));
        books.add(new Book("51ed516f-a", "0375759778", "Prague: A Novel", 2, true));
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public BookDTO create(Book book) {
        try {
            books.add(book);
            return toDTO(book);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new BookDTO();
        }
    }

    @Override
    public BookDTO findById(String id) {
        for (Book book : books) {
            if (book.getBookId().equals(id)) {
                return toDTO(book);
            }
        }
        return new BookDTO();
    }

    @Override
    public BookDTO findByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return toDTO(book);
            }
        }
        return new BookDTO();
    }

    @Override
    public BookDTO update(String id, BookDTO book) {
        try {
            for (Book libro : books) {
                if (libro.getBookId().equals(id)) {
                    libro.setIsbn(book.getIsbn());
                    libro.setName(book.getName());
                    libro.setAmount(book.getAmount());
                    libro.setAvailable(book.getAvailable());
                    return toDTO(libro);
                }
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
            for (Book libro : books) {
                if (libro.getBookId().equals(id)) {
                    books.remove(libro);
                    return "El libro fue eliminado de forma correcta";
                }
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
