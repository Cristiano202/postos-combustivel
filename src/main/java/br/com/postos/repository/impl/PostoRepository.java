package br.com.postos.repository.impl;

import br.com.postos.model.Posto;
import java.util.List;
import java.util.Optional;

public interface PostoRepository {

    List<Posto> findAll();

    Optional<Posto> findById(Long id);

    Optional<Posto> findByCnpj(String cnpj);

    List<Posto> findByBairro(String bairro);

    List<Posto> findByCidade(String cidade);

    List<Posto> findByBandeira(String bandeira);

    Posto save(Posto posto);

    void deleteById(Long id);
}