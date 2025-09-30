package com.parcial.uno.parcial_uno.repository;

import com.parcial.uno.parcial_uno.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    // Puedes agregar métodos personalizados aquí si lo necesitas
}
