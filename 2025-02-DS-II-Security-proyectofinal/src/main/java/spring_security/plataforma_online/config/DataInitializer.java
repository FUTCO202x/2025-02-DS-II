package spring_security.plataforma_online.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring_security.plataforma_online.model.Comentario;
import spring_security.plataforma_online.model.Producto;
import spring_security.plataforma_online.model.Usuario;
import spring_security.plataforma_online.repository.ComentarioRepository;
import spring_security.plataforma_online.repository.ProductoRepository;
import spring_security.plataforma_online.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Primero borramos todo lo que había antes, para empezar desde cero cada vez que se inicia la app
        usuarioRepository.deleteAll();
        productoRepository.deleteAll();
        comentarioRepository.deleteAll();

        // Creamos 5 usuarios de ejemplo, con datos conocidos para poder probar el sistema fácilmente
        Usuario u1 = new Usuario();
        u1.setNombre("Juan Pérez");
        u1.setCorreoElectronico("juan.perez@email.com");
        u1.setContrasena(passwordEncoder.encode("Qwerty123"));
        u1.setDireccion("Carrera 45 #10-20");
        u1.setMetodoDePago("Tarjeta de crédito");

        Usuario u2 = new Usuario();
        u2.setNombre("Ana Gómez");
        u2.setCorreoElectronico("ana.gomez@email.com");
        u2.setContrasena(passwordEncoder.encode("Pass456"));
        u2.setDireccion("Calle 21 #35-50");
        u2.setMetodoDePago("PayPal");

        Usuario u3 = new Usuario();
        u3.setNombre("Carlos Ruiz");
        u3.setCorreoElectronico("carlos.ruiz@email.com");
        u3.setContrasena(passwordEncoder.encode("Segura789"));
        u3.setDireccion("Avenida Principal #100");
        u3.setMetodoDePago("Transferencia bancaria");

        Usuario u4 = new Usuario();
        u4.setNombre("Sofía Martínez");
        u4.setCorreoElectronico("sofia.martinez@email.com");
        u4.setContrasena(passwordEncoder.encode("Clave987"));
        u4.setDireccion("Calle 8 #20-30");
        u4.setMetodoDePago("Efectivo");

        Usuario u5 = new Usuario();
        u5.setNombre("Diego Fernández");
        u5.setCorreoElectronico("diego.fernandez@email.com");
        u5.setContrasena(passwordEncoder.encode("Contra654"));
        u5.setDireccion("Carrera 77 #40-60");
        u5.setMetodoDePago("Tarjeta débito");

        usuarioRepository.saveAll(List.of(u1, u2, u3, u4, u5));

        // Ahora agregamos más usuarios para que haya variedad en los comentarios y compras
        String[] allUserNames = {
            "Juan Pérez", "Ana Gómez", "Carlos Ruiz", "Sofía Martínez", "Diego Fernández",
            "Lucía Rodríguez", "Andrés Ramírez", "María García", "Javier Martínez", "Carolina López",
            "Daniel Castro", "Paola Herrera", "Esteban Rojas", "Fernanda Sánchez", "Camilo Torres",
            "Gabriela Suárez", "Raúl Espinosa", "Verónica Mendoza", "Fabio Jiménez", "Ricardo Vargas",
            "Silvia Gómez", "Martín Aguilar", "Valentina Pérez", "José Ramírez", "Natalia Correa",
            "Julio Fernández", "Amanda Castro", "Pedro Duarte", "Isabela Medina", "Oscar Rodríguez",
            "Cristina Vargas", "Mario Hernández", "Sofía Ramírez", "Andrea Gutiérrez", "Pablo Medina",
            "Patricia López", "Gonzalo Espinoza", "Elena Herrera", "Diego Soto", "Miguel Rojas",
            "Estefanía Carrillo", "Manuel Vargas", "Luisa Mejía", "Victoria Torres", "Federico Montoya",
            "Tomás Méndez", "Sandra Ortiz", "Rodrigo Silva", "Mariana López", "Fernando García"
        };

        String[] metodos = new String[]{"Tarjeta de crédito", "PayPal", "Transferencia bancaria", "Efectivo", "Tarjeta débito"}; // Formas de pago que puede tener cada usuario
        List<Usuario> adicionales = new ArrayList<>();
        for (int i = 5; i < allUserNames.length; i++) {
            Usuario u = new Usuario();
            String nombre = allUserNames[i];
            u.setNombre(nombre);
            // Creamos un correo electrónico para cada usuario usando su nombre
            String[] partes = nombre.toLowerCase().split(" ");
            String local = partes[0].replaceAll("[^a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]", "") + "." + partes[1].replaceAll("[^a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]", "");
            String email = local.replaceAll("[áÁ]", "a").replaceAll("[éÉ]", "e").replaceAll("[íÍ]", "i").replaceAll("[óÓ]", "o").replaceAll("[úÚ]", "u").replaceAll("[ñÑ]", "n").toLowerCase() + "@email.com";
            u.setCorreoElectronico(email);
            // La contraseña es aleatoria para cada usuario adicional
            String randomRaw = UUID.randomUUID().toString().substring(0, 8);
            u.setContrasena(passwordEncoder.encode(randomRaw));
            // Dirección y método de pago también se asignan automáticamente
            u.setDireccion("Calle " + (100 + i) + " #" + ((i + 1) * 3 % 100));
            u.setMetodoDePago(metodos[i % metodos.length]);
            adicionales.add(u);
        }
        if (!adicionales.isEmpty()) {
            usuarioRepository.saveAll(adicionales);
        }

        // Ahora creamos una lista de productos variados para la tienda
        String[] names = new String[]{
            "Laptop", "Smartphone", "Tablet", "Auriculares", "Teclado", "Mouse", "Monitor", "Impresora", "Cámara", "Smartwatch",
            "Silla Gamer", "Microondas", "Refrigerador", "Lavadora", "Cafetera", "Drone", "Bocina Bluetooth", "Videocámara", "TV LED", "Batería Externa",
            "Disco Duro", "Memoria USB", "Router", "Joystick", "Fuente de Poder", "SSD", "Altavoces", "Webcam", "Procesador", "Motherboard",
            "Memoria RAM", "Fuente Solar", "Control Remoto", "Termostato", "Smart Lock", "Proyector", "Switch Ethernet", "Reloj Digital", "Luces LED", "Estabilizador",
            "Cargador Inalámbrico", "HDD Externo", "Micrófono", "Altavoz Inteligente", "Antena Wi-Fi", "Climatizador", "Raspberry Pi", "Capturadora", "Smart Plug", "Timbre Inteligente"
        };

        String[] descriptions = new String[]{
            "Portátil con pantalla Full HD y SSD de 512GB",
            "Teléfono con cámara de 108MP y carga rápida",
            "Dispositivo con pantalla táctil de 10 pulgadas",
            "Audífonos inalámbricos con cancelación de ruido",
            "Teclado mecánico con iluminación RGB",
            "Ratón inalámbrico con sensor óptico de alta precisión",
            "Pantalla LED 4K de 27 pulgadas",
            "Láser multifuncional con Wi-Fi",
            "Cámara digital con lente profesional",
            "Reloj inteligente con GPS y monitoreo cardíaco",
            "Silla ergonómica ajustable con soporte lumbar",
            "Horno microondas con múltiples funciones",
            "Frigorífico doble puerta con sistema No Frost",
            "Lavadora automática con capacidad de 10kg",
            "Cafetera express con vaporizador de leche",
            "Drone con cámara 4K y estabilizador",
            "Altavoz portátil con sonido envolvente",
            "Videocámara profesional con grabación en 4K",
            "Televisor inteligente de 55 pulgadas con HDR",
            "Batería de 20000mAh con carga rápida",
            "Disco duro externo de 2TB",
            "Pendrive de 128GB",
            "Router Wi-Fi 6 de alta velocidad",
            "Control inalámbrico para videojuegos",
            "Fuente de alimentación para PC de 750W",
            "Unidad de almacenamiento SSD de 1TB",
            "Par de bocinas estéreo con subwoofer",
            "Cámara web Full HD con micrófono integrado",
            "CPU Intel i7 de última generación",
            "Placa base compatible con procesadores modernos",
            "Módulo de RAM DDR4 de 16GB",
            "Panel solar portátil con batería integrada",
            "Mando universal para TV y dispositivos",
            "Termostato digital programable",
            "Cerradura electrónica con huella digital",
            "Proyector LED con resolución Full HD",
            "Switch de red de 8 puertos",
            "Reloj inteligente con pantalla AMOLED",
            "Tiras LED RGB con control remoto",
            "Estabilizador de voltaje para dispositivos electrónicos",
            "Base de carga inalámbrica rápida",
            "Disco duro portátil de 4TB",
            "Micrófono profesional para grabación",
            "Asistente de voz con altavoz integrado",
            "Amplificador de señal inalámbrico",
            "Aire acondicionado portátil con control remoto",
            "Kit de desarrollo con Raspberry Pi 4",
            "Placa de captura de video en alta resolución",
            "Enchufe inteligente compatible con asistentes virtuales",
            "Timbre con cámara y conexión a Wi-Fi"
        };

        double[] prices = new double[]{
            89999,49950,29999,12999,8999,5999,49900,17999,79999,19999,
            29999,12999,119999,59999,14999,69999,8999,99999,74999,19999,
            12999,2999,19999,7999,8999,14999,13999,6999,34999,19999,
            7999,24999,2499,9999,19999,29999,5999,8999,3999,15999,
            4999,17999,14999,12999,7999,29999,12999,19999,3999,14999
        };

        int[] stocks = new int[]{
            10,20,15,25,30,50,12,18,8,22,
            14,40,5,7,35,9,33,6,11,45,
            28,60,16,20,17,32,23,37,9,13,
            41,4,50,22,6,12,38,26,55,10,
            30,15,7,20,33,5,19,8,42,10
        };

        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Producto p = new Producto();
            p.setNombre(names[i]);
            p.setDescripcion(descriptions[i]);
            p.setPrecio(prices[i]);
            p.setStock(stocks[i]);
            productos.add(p);
        }
        // Guardamos todos los productos en la base de datos
        productoRepository.saveAll(productos);

        // Hacemos un mapa para poder encontrar rápidamente a cada usuario por su nombre
        Map<String, Usuario> usuariosMap = new HashMap<>();
        List<Usuario> todosUsuarios = usuarioRepository.findAll();
        for (Usuario u : todosUsuarios) {
            usuariosMap.put(u.getNombre(), u);
        }

        // Finalmente, creamos 50 comentarios de ejemplo, cada uno con su usuario, producto, texto y fecha
        Object[][] comentariosData = {
            {1, "Juan Pérez", "Excelente rendimiento; muy rápida. ¡Me encanta!", LocalDate.of(2025, 5, 1)},
            {2, "Ana Gómez", "Buena cámara pero la batería dura poco.", LocalDate.of(2025, 5, 3)},
            {3, "Carlos Ruiz", "No me gustó; pantalla de baja calidad.", LocalDate.of(2025, 5, 5)},
            {4, "Sofía Martínez", "Sonido aceptable pero el material parece frágil.", LocalDate.of(2025, 5, 6)},
            {5, "Diego Fernández", "Muy buen teclado mecánico; excelente respuesta.", LocalDate.of(2025, 5, 8)},
            {6, "Ana Gómez", "El sensor no es tan preciso como esperaba.", LocalDate.of(2025, 5, 10)},
            {7, "Carlos Ruiz", "Colores vibrantes y buena resolución. Muy satisfecho.", LocalDate.of(2025, 5, 12)},
            {8, "Juan Pérez", "Tarda mucho en imprimir; no me convence.", LocalDate.of(2025, 5, 13)},
            {9, "Sofía Martínez", "Increíble calidad de imagen; fotos súper nítidas.", LocalDate.of(2025, 5, 15)},
            {10, "Diego Fernández", "Buena batería; pero la pantalla no es muy brillante.", LocalDate.of(2025, 5, 18)},
            {11, "Lucía Rodríguez", "Comodidad espectacular; perfecto para largas sesiones de juego.", LocalDate.of(2025, 5, 20)},
            {12, "Andrés Ramírez", "Calienta bien pero hace mucho ruido.", LocalDate.of(2025, 5, 22)},
            {13, "María García", "Espacioso y enfría rápido; muy recomendado.", LocalDate.of(2025, 5, 24)},
            {14, "Javier Martínez", "Lava bien pero el ciclo es muy largo.", LocalDate.of(2025, 5, 26)},
            {15, "Carolina López", "Hace café delicioso; fácil de usar.", LocalDate.of(2025, 5, 28)},
            {16, "Daniel Castro", "Muy divertido pero la batería dura poco.", LocalDate.of(2025, 5, 30)},
            {17, "Paola Herrera", "Sonido potente y buena conexión Bluetooth.", LocalDate.of(2025, 6, 1)},
            {18, "Esteban Rojas", "Perfecta para grabaciones profesionales.", LocalDate.of(2025, 6, 3)},
            {19, "Fernanda Sánchez", "Imagen excelente pero el sonido podría mejorar.", LocalDate.of(2025, 6, 5)},
            {20, "Camilo Torres", "Carga bien pero es un poco pesada.", LocalDate.of(2025, 6, 7)},
            {21, "Gabriela Suárez", "Gran capacidad de almacenamiento; funciona rápido.", LocalDate.of(2025, 6, 9)},
            {22, "Raúl Espinosa", "Buen tamaño pero la velocidad de transferencia es baja.", LocalDate.of(2025, 6, 11)},
            {23, "Verónica Mendoza", "Señal potente; cubre toda la casa.", LocalDate.of(2025, 6, 13)},
            {24, "Fabio Jiménez", "Comodo y resistente; ideal para gaming.", LocalDate.of(2025, 6, 15)},
            {25, "Ricardo Vargas", "Funciona bien pero los cables son muy cortos.", LocalDate.of(2025, 6, 17)},
            {26, "Silvia Gómez", "Velocidad increíble; mi PC va mucho más rápido ahora.", LocalDate.of(2025, 6, 19)},
            {27, "Martín Aguilar", "Sonido envolvente; muy buena compra.", LocalDate.of(2025, 6, 21)},
            {28, "Valentina Pérez", "Imagen clara pero el micrófono es deficiente.", LocalDate.of(2025, 6, 23)},
            {29, "José Ramírez", "Rendimiento impecable; ideal para gaming y diseño.", LocalDate.of(2025, 6, 25)},
            {30, "Natalia Correa", "Buenas prestaciones pero la instalación fue complicada.", LocalDate.of(2025, 6, 27)},
            {31, "Julio Fernández", "Expande muy bien el rendimiento del sistema.", LocalDate.of(2025, 6, 29)},
            {32, "Amanda Castro", "Energía confiable pero la batería es pequeña.", LocalDate.of(2025, 7, 1)},
            {33, "Pedro Duarte", "Fácil de usar; reconoce muchos dispositivos.", LocalDate.of(2025, 7, 3)},
            {34, "Isabela Medina", "Regula bien la temperatura; intuitivo de usar.", LocalDate.of(2025, 7, 5)},
            {35, "Oscar Rodríguez", "Seguridad y tecnología en un solo dispositivo.", LocalDate.of(2025, 7, 7)},
            {36, "Cristina Vargas", "Imagen nítida pero requiere una sala oscura.", LocalDate.of(2025, 7, 9)},
            {37, "Mario Hernández", "Buena velocidad de conexión; estable.", LocalDate.of(2025, 7, 11)},
            {38, "Sofía Ramírez", "Pantalla atractiva pero la batería dura poco.", LocalDate.of(2025, 7, 13)},
            {39, "Andrea Gutiérrez", "Buenas opciones de colores; buen diseño.", LocalDate.of(2025, 7, 15)},
            {40, "Pablo Medina", "Protege bien contra variaciones de voltaje.", LocalDate.of(2025, 7, 17)},
            {41, "Patricia López", "Carga rápido pero requiere posicionamiento preciso.", LocalDate.of(2025, 7, 19)},
            {42, "Gonzalo Espinoza", "Mucho espacio; resistente y confiable.", LocalDate.of(2025, 7, 21)},
            {43, "Elena Herrera", "Calidad de sonido profesional; ideal para podcast.", LocalDate.of(2025, 7, 23)},
            {44, "Diego Soto", "Responde bien a comandos de voz; útil en casa.", LocalDate.of(2025, 7, 25)},
            {45, "Miguel Rojas", "Amplifica bien la señal pero el rango es limitado.", LocalDate.of(2025, 7, 27)},
            {46, "Estefanía Carrillo", "Enfría rápido pero es algo ruidoso.", LocalDate.of(2025, 7, 29)},
            {47, "Manuel Vargas", "Perfecto para proyectos electrónicos y programación.", LocalDate.of(2025, 7, 31)},
            {48, "Luisa Mejía", "Ideal para streaming y grabaciones en alta calidad.", LocalDate.of(2025, 8, 2)},
            {49, "Victoria Torres", "Muy práctico; fácil de conectar y configurar.", LocalDate.of(2025, 8, 4)},
            {50, "Federico Montoya", "Buena cámara; útil para seguridad.", LocalDate.of(2025, 8, 6)}
        };

        List<Comentario> comentarios = new ArrayList<>();
        for (Object[] datos : comentariosData) {
            int productoId = (Integer) datos[0];
            String nombreUsuario = (String) datos[1];
            String textoComentario = (String) datos[2];
            LocalDate fecha = (LocalDate) datos[3];

            Comentario c = new Comentario();
            // Buscamos el producto usando el número, restando 1 porque la lista empieza en cero
            c.setProducto(productos.get(productoId - 1));
            Usuario usuario = usuariosMap.get(nombreUsuario);
            if (usuario == null) {
                // Si no encontramos el usuario, usamos el primero de la lista para no dejar el comentario sin dueño
                usuario = todosUsuarios.get(0);
            }
            c.setUsuario(usuario);
            c.setComentario(textoComentario);
            c.setFecha(fecha);
            comentarios.add(c);
        }
        // Guardamos todos los comentarios en la base de datos
        comentarioRepository.saveAll(comentarios);
    }
}