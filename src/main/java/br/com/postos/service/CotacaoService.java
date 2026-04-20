package br.com.postos.service;

import br.com.postos.model.CotacaoCombustivel;
import br.com.postos.repository.CotacaoCombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoCombustivelRepository repository;

    @Transactional
    public CotacaoCombustivel salvar(CotacaoCombustivel cotacao) {
        try {
            // O ID de 6 dígitos será gerado automaticamente pelo @PrePersist na Entity
            return repository.save(cotacao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a cotação: " + e.getMessage());
        }
    }

    public List<CotacaoCombustivel> listarTodas() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível listar as cotações: " + e.getMessage());
        }
    }

    public Optional<CotacaoCombustivel> buscarPorId(String id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao localizar cotação ID: " + id);
        }
    }

    public List<CotacaoCombustivel> buscarPorPosto(String postoId) {
        try {
            return repository.findByPostoId(postoId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cotações para o posto selecionado.");
        }
    }

    @Transactional
    public void deletar(String id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new RuntimeException("Cotação não encontrada!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao excluir cotação: " + e.getMessage());
        }
    }
}