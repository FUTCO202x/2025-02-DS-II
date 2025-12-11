package spring_security.plataforma_online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring_security.plataforma_online.model.Comentario;
import spring_security.plataforma_online.repository.ComentarioRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public List<Comentario> listFromDate(@RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from) {
        return comentarioRepository.findByFechaAfter(from.minusDays(1));
    }
}
