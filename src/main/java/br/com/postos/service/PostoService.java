package br.com.postos.service;

import br.com.postos.model.Posto;
import br.com.postos.repository.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostoService {

    @Autowired
    private PostoRepository repository;

    @Transactional
    public Posto salvar(Posto posto) {
        try {
            // Regra: Não deixar cadastrar o mesmo CNPJ duas vezes
            Optional<Posto> postoExistente = repository.findByCnpj(posto.getCnpj());
            if (postoExistente.isPresent() && !postoExistente.get().getId().equals(posto.getId())) {
                throw new RuntimeException("Já existe um posto cadastrado com este CNPJ!");
            }
            return repository.save(posto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o posto: " + e.getMessage());
        }
    }

    public List<Posto> listarTodos() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar postos: " + e.getMessage());
        }
    }

    public Optional<Posto> buscarPorId(String id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar posto por ID: " + id);
        }
    }

    @Transactional
    public void deletar(String id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new RuntimeException("Posto não encontrado!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar o posto: " + e.getMessage());
        }
    }


    public Posto buscarPorCnpj(String cnpj) {
        return repository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Posto com CNPJ " + cnpj + " não encontrado."));
    }
}