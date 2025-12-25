package spring_security.plataforma_online.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring_security.plataforma_online.model.Usuario;
import spring_security.plataforma_online.repository.UsuarioRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        Optional<Usuario> exists = usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico());
        if (exists.isPresent()) {
            return ResponseEntity.badRequest().body("Correo ya registrado");
        }
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena()));
            String token = jwtUtil.generateToken(request.getCorreo());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
