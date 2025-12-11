# Plataforma Online

Este proyecto es una tienda en línea desarrollada con Java y Spring Boot.

## ¿Cómo funciona?

Al iniciar la aplicación, se crean usuarios, productos y comentarios de ejemplo para facilitar las pruebas.

La aplicación utiliza el puerto **9000**, por lo que todas las URLs comienzan con:
`http://localhost:9000/api/`

---

## Endpoints principales y ejemplos para probar

### 1. Registrar usuario
- **Endpoint principal:**
  - **POST** `http://localhost:9000/api/auth/register`
  - **Body (JSON):**
    ```json
    {
      "nombre": "Lucía Torres",
      "correoElectronico": "lucia.torres@email.com",
      "contrasena": "segura2025",
      "direccion": "Calle 123 #45-67",
      "metodoDePago": "Tarjeta de crédito"
    }
    ```
  - `Content-Type: application/json` (No es necesario especificar el contenido en el Headers, basta con cargar el json en el body)

### 2. Iniciar sesión (login)
- **Endpoint principal:**
  - **POST** `http://localhost:9000/api/auth/login`
  - **Body (JSON):**
    ```json
    {
      "correo": "juan.perez@email.com",
      "contrasena": "Qwerty123"
    }
    ```
  - `Content-Type: application/json`
  - **Respuesta:** Devuelve un token JWT para usar en endpoints protegidos.

### 3. Ver productos
- **Endpoint principal:**
  - **GET** `http://localhost:9000/api/products`
  - **Headers:** No se requiere token.
- **Funcionalidad extra:**
  - Filtrar productos menores a stock especificado:
    - **GET** `http://localhost:9000/api/products?stockThreshold=8`

### 4. Ver comentarios
- **Endpoint principal:**
  - **GET** `http://localhost:9000/api/comments?from=2025-05-01`
    (El parámetro `from` es la fecha desde la que se quieren ver los comentarios, formato YYYY-MM-DD)
  - **Headers:** No se requiere token.
- **Funcionalidad extra:**
  - Se puede cambiar el parámetro `from` para filtrar comentarios desde otra fecha:
    - **GET** `http://localhost:9000/api/comments?from=2025-06-10`

### 5. Crear carrito de compras
- **Endpoint principal:**
  - **POST** `http://localhost:9000/api/cart/create`
  - **Body (JSON):**
    ```json
    {
      "productoIds": [1, 2, 5]
    }
    ```
    (Solo se envía la lista de IDs de productos que quieres agregar al carrito)
  - 
    - `Content-Type: application/json`
    - `Authorization: Bearer <tu_token_jwt>`

### 6. Ver productos de tu carrito
- **Endpoint principal:**
  - **GET** `http://localhost:9000/api/cart/{id}/products`
    (Reemplazar `{id}` con el ID del carrito)
  - `Authorization: Bearer <tu_token_jwt>`


//integrantes
Jean Patiño
Jesus Noriega
Samir Polo
Orlando Beltran