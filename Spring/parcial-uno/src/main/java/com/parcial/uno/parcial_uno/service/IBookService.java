// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.parcial.uno.parcial_uno.service;

import com.parcial.uno.parcial_uno.dtos.BookDTO;
import com.parcial.uno.parcial_uno.model.Book;
import java.util.List;

public interface IBookService {
   List<BookDTO> getAll();

   BookDTO create(Book var1);

   BookDTO findById(String var1);

   BookDTO findByISBN(String var1);

   BookDTO update(String var1, BookDTO var2);

   String delete(String var1);
}