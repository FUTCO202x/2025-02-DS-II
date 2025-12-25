package com.parcial.uno.parcial_uno.service;

import java.util.List;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;

public interface IBookService {
    List<Book> getAll(); // Este es implementado por el profesor
    BookDTO create(Book book);
    BookDTO findById(String id);
    BookDTO findByISBN(String isbn);
    BookDTO update(String id, BookDTO book);
    String delete(String id);
}