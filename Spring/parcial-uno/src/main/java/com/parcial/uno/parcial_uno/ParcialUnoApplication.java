package com.parcial.uno.parcial_uno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParcialUnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcialUnoApplication.class, args);
	}

}

/* Listar todos los libros	GET	http://localhost:9000/api/books
 * Crear un libro	POST	http://localhost:9000/api/books
 * Buscar por ID	GET	http://localhost:9000/api/books/{id}
 * Buscar por ISBN	GET	http://localhost:9000/api/books/isbn/{isbn}
 * Actualizar libro	PUT	http://localhost:9000/api/books/{id}
 * Eliminar libro	DELETE	http://localhost:9000/api/books/{id}
 * Buscar por parámetros (isbn y name)	POST	http://localhost:9000/api/books/params?isbn=xxx&name=yyy
 */
