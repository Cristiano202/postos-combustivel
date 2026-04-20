package br.com.postos.service.Impl;

import br.com.postos.model.CotacaoCombustivel;
import br.com.postos.model.Posto;
import br.com.postos.model.enums.TipoCombustivel;
import br.com.postos.repository.PostoRepository;
import br.com.postos.service.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PostoServiceImpl implements PostoService {
    private final PostoRepository postoRepository;
    private final AtomicLong contadorCotacaoId = new AtomicLong(1);

    @Autowired
    public PostoServiceImpl(PostoRepository postoRepository) {
        this.postoRepository = postoRepository;
    }

    @Override
    public Posto criarPosto(Posto posto) {
        // Validações básicas
        if (posto.getCnpj() == null || posto.getCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ é obrigatório");
        }
        // Verificar duplicidade de CNPJ
        if (postoRepository.findByCnpj(posto.getCnpj()).isPresent()) {
            throw new RuntimeException("Já existe um posto com este CNPJ");
        }
        // Outras validações poderiam ser adicionadas (nome, endereço, etc.)
        return postoRepository.save(posto);
    }

    @Override
    public List<Posto> listarTodos() {
        return postoRepository.findAll();
    }

    @Override
    public Posto buscarPorId(Long id) {
        return postoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posto não encontrado com id: " + id));
    }

    @Override
    public Posto atualizarPosto(Long id, Posto postoAtualizado) {
        Posto postoExistente = buscarPorId(id); // já lança exceção se não existir
        // Atualiza apenas campos permitidos
        postoExistente.setNomeFantasia(postoAtualizado.getNomeFantasia());
        postoExistente.setBandeira(postoAtualizado.getBandeira());
        postoExistente.setEndereco(postoAtualizado.getEndereco());
        postoExistente.setBairro(postoAtualizado.getBairro());
        postoExistente.setCidade(postoAtualizado.getCidade());
        postoExistente.setEstado(postoAtualizado.getEstado());
        postoExistente.setTelefone(postoAtualizado.getTelefone());
        // A lista de cotações não é alterada aqui
        return postoRepository.save(postoExistente);
    }

    @Override
    public void deletarPosto(Long id) {
        if (!postoRepository.findById(id).isPresent()) {
            throw new RuntimeException("Posto não encontrado com id: " + id);
        }
        postoRepository.deleteById(id);
    }

    @Override
    public CotacaoCombustivel adicionarCotacao(Long postoId, CotacaoCombustivel cotacao) {
        Posto posto = buscarPorId(postoId);
        // Validações da cotação
        if (cotacao.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (cotacao.getDataColeta() == null) {
            cotacao.setDataColeta(LocalDate.now());
        }
        // Gera ID para a cotação (simulação)
        cotacao.setId(contadorCotacaoId.getAndIncrement());
        // Associa a cotação ao posto
        posto.adicionarCotacao(cotacao);
        // Salva o posto (com a nova cotação na lista)
        postoRepository.save(posto);
        return cotacao;
    }

    @Override
    public List<CotacaoCombustivel> listarCotacoesDoPosto(Long postoId) {
        Posto posto = buscarPorId(postoId);
        return posto.getCotacoes(); // pode ser null? No construtor inicializamos como ArrayList vazio
    }

    @Override
    public List<Posto> buscarPorBairro(String bairro) {
        return postoRepository.findByBairro(bairro);
    }

    @Override
    public List<Posto> buscarPorCidade(String cidade) {
        return postoRepository.findByCidade(cidade);
    }

    @Override
    public List<Posto> buscarPorBandeira(String bandeira) {
        return postoRepository.findByBandeira(bandeira);
    }

    @Override
    public Double calcularPrecoMedio(TipoCombustivel tipo, String bairro) {
        List<Posto> postos = postoRepository.findByBairro(bairro);
        return postos.stream()
                .flatMap(p -> p.getCotacoes().stream())
                .filter(c -> c.getTipo() == tipo)
                .mapToDouble(CotacaoCombustivel::getPreco)
                .average()
                .orElse(0.0);
    }

    @Override
    public Posto encontrarMenorPreco(TipoCombustivel tipo, String cidade) {
        List<Posto> postos = postoRepository.findByCidade(cidade);
        Posto menorPrecoPosto = null;
        double menorPreco = Double.MAX_VALUE;
        for (Posto p : postos) {
            for (CotacaoCombustivel c : p.getCotacoes()) {
                if (c.getTipo() == tipo && c.getPreco() < menorPreco) {
                    menorPreco = c.getPreco();
                    menorPrecoPosto = p;
                }
            }
        }
        return menorPrecoPosto; // pode retornar null se não encontrar nenhuma cotação
    }
}
