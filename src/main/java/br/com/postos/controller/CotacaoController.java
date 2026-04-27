package br.com.postos.controller;

import br.com.postos.model.CotacaoCombustivel;
import br.com.postos.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotacoes")
@CrossOrigin(origins = "*") // Permite a conexão com o seu React
public class CotacaoController {

    @Autowired
    private CotacaoService service;

    // Endpoint para registrar um novo preço
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CotacaoCombustivel cotacao) {
        try {
            CotacaoCombustivel nova = service.salvar(cotacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(nova);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Lista todas as cotações do sistema
    @GetMapping
    public ResponseEntity<List<CotacaoCombustivel>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // Busca cotações de um posto específico
    @GetMapping("/posto/{postoId}")
    public ResponseEntity<?> listarPorPosto(@PathVariable String postoId) {
        try {
            return ResponseEntity.ok(service.buscarPorPosto(postoId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Deleta uma cotação por ID
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