# 🚀 Spring Boot - Gestión de Usuarios (Sin Base de Datos)

Este proyecto es una aplicación sencilla desarrollada con **Spring Boot**, orientada a demostrar buenas prácticas de desacoplamiento usando **Controllers**, **Models**, **DTOs** y **Services**, sin necesidad de una base de datos.

El servidor corre en el puerto `9000`.

---

## 📦 Funcionalidades

### 1. 🆕 Crear Usuario

- **Endpoint:** `POST /api/usuarios`
- **Request Body:**
  ```json
  {
    "id": 1,
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@example.com"
  }


- **Respuesta esperada:**
  ```json
  {
    "nombre": "JUAN",
    "apellido": "PÉREZ"
  }

### 2. 🆕 📋 Obtener Todos los Usuarios
- **Endpoint:** `GET /api/usuarios`
- **Descripción:** Devuelve una lista de 3 usuarios predefinidos en memoria (`List<Usuario>`).

## 3. 🔍 Obtener Usuario por ID

- **Endpoint:** `GET /api/usuarios/{id}`
- **Ejemplo:** `GET /api/usuarios/2`
- **Respuesta esperada:**
  ```json
  {
    "id": 2,
    "nombre": "MARÍA",
    "apellido": "GÓMEZ",
    "email": "maria@example.com"
  }

## 4. 🌐 Parámetros en URL

- **Endpoint:** `GET /api/params`
- **Ejemplo:**  
  `http://localhost:9000/api/params?nombre=Luis&apellido=Torres`
- **Respuesta esperada:**
  ```json
  {
    "nombreCompleto": "Luis Torres"
  }

## 🧠 Buenas Prácticas Aplicadas

- Separación clara entre capas (`Controller`, `Service`, `Model`, `DTO`).
- Uso de DTO para controlar la salida de datos.
- Datos en memoria simulando una base de datos.
- Transformación de datos (mayúsculas, concatenación de parámetros).