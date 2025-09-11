# Parcial 1
Se requiere hacer los siguiente:

* Hacer un fork de esta rama a su git personal.
* Hacer un git clone de su repositorio a su computador local.
* Realizar el codigo solicitado, en la sección de REQUERIMIENTOS esta lo que se deberá realizar
* Hacer un push de su computador a su Git Personal.
* Hacer un Pull Request al repositorio original (Repositorio del profesor).


# REQUERIMIENTOS

### 1 Crear Libros
Crear libros con los datos que se tienen en la lista
### 2 Encontrar Libro por Id
Encontrar libro por id
### 3 Encontrar Libro por ISBN
Encontrar libro por id
### 4 Actualizar Libro
Actualizar libro enviando el Id y los datos a cambiar
### 5 Eliminar Libro
Eliminar el libro enviando el Id
### 6 Buscar por Parametros
Buscar por parametros de isbn y name y traer el json de la siguiente forma:
- **Endpoint:** `POST /api/books/params?isbn=xxx&name=yyy`
- **Response Body:**
  ```json
    {
    "name": "Johanny",
    "amount": 5,
    "available": true
    }



### Información Adicional
Para tener en cuenta:

* Ya tienen un listado de libros creados, pueden usar ese listado, se creara al momento de ejecutar el programa

