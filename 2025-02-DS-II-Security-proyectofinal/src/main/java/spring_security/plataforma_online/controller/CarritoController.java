package spring_security.plataforma_online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import spring_security.plataforma_online.controller.dto.CreateCartRequest;
import spring_security.plataforma_online.model.CarritoDeCompras;
import spring_security.plataforma_online.model.Producto;
import spring_security.plataforma_online.model.Usuario;
import spring_security.plataforma_online.repository.CarritoRepository;
import spring_security.plataforma_online.repository.ProductoRepository;
import spring_security.plataforma_online.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CarritoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createCart(@RequestBody CreateCartRequest req, Authentication authentication) {
        String correo = authentication.getName();
        Usuario usuario = usuarioRepository.findByCorreoElectronico(correo).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
        CarritoDeCompras carrito = new CarritoDeCompras();
        carrito.setUsuario(usuario);
        List<Producto> productos = new ArrayList<>();
        double subtotal = 0.0;
        for (Long pid : req.getProductoIds()) {
            Producto p = productoRepository.findById(pid).orElse(null);
            if (p == null) continue;
            if (p.getStock() <= 0) continue;
            p.setStock(p.getStock() - 1);
            productoRepository.save(p);
            productos.add(p);
            subtotal += p.getPrecio();
        }
        carrito.setProductos(productos);
        carrito.setSubtotal(subtotal);
        double impuestos = subtotal * 0.19; // ejemplo IVA 19%
        carrito.setImpuestos(impuestos);
        carritoRepository.save(carrito);
        return ResponseEntity.ok(carrito);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> listCartProducts(@PathVariable("id") Long id, Authentication authentication) {
        String correo = authentication.getName();
        Usuario usuario = usuarioRepository.findByCorreoElectronico(correo).orElse(null);
        if (usuario == null) return ResponseEntity.status(401).body("Usuario no encontrado");
        java.util.Optional<spring_security.plataforma_online.model.CarritoDeCompras> opt = carritoRepository.findByIdCarritoAndUsuario_IdUsuario(id, usuario.getIdUsuario());
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get().getProductos());
        } else {
            return ResponseEntity.status(403).body("Carrito no encontrado o no pertenece al usuario");
        }
    }
}
