package br.com.postos.repository.impl;

import br.com.postos.model.Posto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostoRepositoryImpl implements PostoRepository{
    private final List<Posto> postos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    @Override
    public List<Posto> findAll() {
        // Retorna uma cópia da lista para evitar modificações externas
        return new ArrayList<>(postos);
    }

    @Override
    public Optional<Posto> findById(Long id) {
        return postos.stream()
                .filter(posto -> posto.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Posto> findByCnpj(String cnpj) {
        return postos.stream()
                .filter(posto -> posto.getCnpj().equals(cnpj))
                .findFirst();
    }

    @Override
    public List<Posto> findByBairro(String bairro) {
        return postos.stream()
                .filter(posto -> posto.getBairro().equalsIgnoreCase(bairro))
                .collect(Collectors.toList());
    }

    @Override
    public List<Posto> findByCidade(String cidade) {
        return postos.stream()
                .filter(posto -> posto.getCidade().equalsIgnoreCase(cidade))
                .collect(Collectors.toList());
    }

    @Override
    public List<Posto> findByBandeira(String bandeira) {
        return postos.stream()
                .filter(posto -> posto.getBandeira().equalsIgnoreCase(bandeira))
                .collect(Collectors.toList());
    }

    @Override
    public Posto save(Posto posto) {
        if (posto.getId() == null) {
            // Novo posto: gera ID e adiciona à lista
            posto.setId(contadorId.getAndIncrement());
            postos.add(posto);
        } else {
            // Atualização: remove o antigo e adiciona o atualizado
            deleteById(posto.getId());
            postos.add(posto);
        }
        return posto;
    }

    @Override
    public void deleteById(Long id) {
        postos.removeIf(posto -> posto.getId().equals(id));
    }
}
