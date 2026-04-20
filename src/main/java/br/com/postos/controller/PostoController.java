package br.com.postos.controller;

import br.com.postos.model.Posto;
import br.com.postos.service.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postos")
@CrossOrigin(origins = "*") // Isso aqui é vital para o s React conseguir conversar com o Java!
public class PostoController {

    @Autowired
    private PostoService service;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Posto posto) {
        try {
            Posto novoPosto = service.salvar(posto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPosto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Posto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(posto -> ResponseEntity.ok(posto))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}