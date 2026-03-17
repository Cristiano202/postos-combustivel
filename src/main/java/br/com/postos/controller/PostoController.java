package br.com.postos.controller;

import br.com.postos.dto.CotacaoRequestDTO;
import br.com.postos.dto.CotacaoResponseDTO;
import br.com.postos.dto.PostoRequestDTO;
import br.com.postos.dto.PostoResponseDTO;
import br.com.postos.mapper.PostoMapper;
import br.com.postos.model.CotacaoCombustivel;
import br.com.postos.model.Posto;
import br.com.postos.model.enums.TipoCombustivel;
import br.com.postos.service.PostoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/postos")
public class PostoController {

    private final PostoService postoService;

    @Autowired
    public PostoController(PostoService postoService) {
        this.postoService = postoService;
    }

    // -------------------- Posto CRUD --------------------

    @PostMapping
    public ResponseEntity<PostoResponseDTO> criarPosto(@Valid @RequestBody PostoRequestDTO request) {
        Posto posto = PostoMapper.toEntity(request);
        Posto postoSalvo = postoService.criarPosto(posto);
        PostoResponseDTO response = PostoMapper.toResponseDTO(postoSalvo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostoResponseDTO>> listarTodos() {
        List<Posto> postos = postoService.listarTodos();
        List<PostoResponseDTO> response = postos.stream()
                .map(PostoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostoResponseDTO> buscarPorId(@PathVariable Long id) {
        Posto posto = postoService.buscarPorId(id);
        PostoResponseDTO response = PostoMapper.toResponseDTO(posto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostoResponseDTO> atualizarPosto(
            @PathVariable Long id,
            @Valid @RequestBody PostoRequestDTO request) {
        Posto posto = PostoMapper.toEntity(request);
        Posto postoAtualizado = postoService.atualizarPosto(id, posto);
        PostoResponseDTO response = PostoMapper.toResponseDTO(postoAtualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPosto(@PathVariable Long id) {
        postoService.deletarPosto(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------- Cotações --------------------

    @PostMapping("/{id}/cotacoes")
    public ResponseEntity<CotacaoResponseDTO> adicionarCotacao(
            @PathVariable Long id,
            @Valid @RequestBody CotacaoRequestDTO request) {
        CotacaoCombustivel cotacao = PostoMapper.toEntity(request);
        CotacaoCombustivel cotacaoSalva = postoService.adicionarCotacao(id, cotacao);
        CotacaoResponseDTO response = PostoMapper.toResponseDTO(cotacaoSalva);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/cotacoes")
    public ResponseEntity<List<CotacaoResponseDTO>> listarCotacoes(@PathVariable Long id) {
        List<CotacaoCombustivel> cotacoes = postoService.listarCotacoesDoPosto(id);
        List<CotacaoResponseDTO> response = cotacoes.stream()
                .map(PostoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // -------------------- Buscas específicas --------------------

    @GetMapping("/busca/bairro")
    public ResponseEntity<List<PostoResponseDTO>> buscarPorBairro(@RequestParam String bairro) {
        List<Posto> postos = postoService.buscarPorBairro(bairro);
        List<PostoResponseDTO> response = postos.stream()
                .map(PostoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/busca/cidade")
    public ResponseEntity<List<PostoResponseDTO>> buscarPorCidade(@RequestParam String cidade) {
        List<Posto> postos = postoService.buscarPorCidade(cidade);
        List<PostoResponseDTO> response = postos.stream()
                .map(PostoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/busca/bandeira")
    public ResponseEntity<List<PostoResponseDTO>> buscarPorBandeira(@RequestParam String bandeira) {
        List<Posto> postos = postoService.buscarPorBandeira(bandeira);
        List<PostoResponseDTO> response = postos.stream()
                .map(PostoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // -------------------- Relatórios --------------------

    @GetMapping("/relatorios/preco-medio")
    public ResponseEntity<Double> calcularPrecoMedio(
            @RequestParam TipoCombustivel tipo,
            @RequestParam String bairro) {
        Double media = postoService.calcularPrecoMedio(tipo, bairro);
        return ResponseEntity.ok(media);
    }

    @GetMapping("/relatorios/menor-preco")
    public ResponseEntity<PostoResponseDTO> encontrarMenorPreco(
            @RequestParam TipoCombustivel tipo,
            @RequestParam String cidade) {
        Posto posto = postoService.encontrarMenorPreco(tipo, cidade);
        if (posto == null) {
            return ResponseEntity.notFound().build();
        }
        PostoResponseDTO response = PostoMapper.toResponseDTO(posto);
        return ResponseEntity.ok(response);
    }
}