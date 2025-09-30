package com.parcial.uno.parcial_uno.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_id", nullable = false, unique = true)
    private String bookId;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "name")
    private String name;
    @Column(name = "available")
    private Boolean available;
    @Column(name = "amount")
    private Integer amount;

    public Book(String bookId, String isbn, String name, Integer amount, Boolean available) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.name = name;
        this.amount = amount;
        this.available = available;
    }

    public Book() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", isbn=" + isbn + ", name=" + name + ", available=" + available + ", amount="
                + amount + "]";
    }

    
}
