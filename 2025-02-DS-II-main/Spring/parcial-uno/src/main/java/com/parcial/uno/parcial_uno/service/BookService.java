package com.parcial.uno.parcial_uno.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;

@Service
public class BookService implements IBookService {
    
    private final List<Book> books = new ArrayList<>();

    public BookService(){
        books.add(new Book("6600ab76-3", "0002005018","Clara Callan", 5, true));
        books.add(new Book("297c17d8-4", "0195153448","Classical Mythology", 3, true));
        books.add(new Book("11b553eb-b", "0399135782","The kitchen God's wife", 8, true));
        books.add(new Book("3c24c2fa-3", "0440234743","The testament", 4, true));
        books.add(new Book("eb25c2d4-7", "0393045218","The mummies of Urumchi", 5, true));
        books.add(new Book("1940136a-2", "0060973129","Decision in Normandy", 3, true));
        books.add(new Book("12a13228-0", "0345402871","Airframe", 1, true));
        books.add(new Book("51ed516f-a", "0375759778","Prague: A Novel", 2, true));
    }

    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getBookId(), book.getIsbn(), book.getName(), book.getAmount(), book.getAvailable());
    }

    @Override
    public List<BookDTO> getAll() {
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO create(Book book) {
        books.add(book);
        return convertToDTO(book);
    }

    @Override
    public BookDTO findById(String id) {
        Optional<Book> found = books.stream().filter(b -> b.getBookId().equals(id)).findFirst();
        return found.map(this::convertToDTO).orElse(null);
    }

    @Override
    public BookDTO findByISBN(String isbn) {
        Optional<Book> found = books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
        return found.map(this::convertToDTO).orElse(null);
    }

    @Override
    public BookDTO update(String id, BookDTO bookDTO) {
        for (Book b : books) {
            if (b.getBookId().equals(id)) {
                b.setName(bookDTO.getName());
                b.setIsbn(bookDTO.getIsbn());
                b.setAmount(bookDTO.getAmount());
                b.setAvailable(bookDTO.getAvailable());
                return convertToDTO(b);
            }
        }
        return null;
    }

    @Override
    public String delete(String id) {
        boolean removed = books.removeIf(b -> b.getBookId().equals(id));
        return removed ? "Libro eliminado" : "Libro no encontrado";
    }
}
