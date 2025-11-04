| Concepto       | Encode (Codificar)                      | Encrypt (Encriptar)                                 | Hash (Ej. bcrypt)                                  |
|----------------|------------------------------------------|-----------------------------------------------------|----------------------------------------------------|
| **Propósito**  | Formato legible para transmisión         | Proteger datos para que solo usuarios autorizados lo lean | Verificar integridad o autenticidad, no reversible |
| **Ejemplos**   | Base64, URL encoding, UTF-8              | AES, RSA, Blowfish                                  | bcrypt, scrypt, Argon2                             |
| **Reversible** | Sí, fácilmente                           | Sí, con clave correcta                              | No (solo verificable)                              |
| **Seguridad**  | Ninguna                                  | Alta (si se usa bien)                               | Alta para contraseñas                              |
| **Uso común**  | Transmisión de datos, URLs, emails       | Comunicación segura, almacenamiento de datos        | Almacenamiento seguro de contraseñas               |