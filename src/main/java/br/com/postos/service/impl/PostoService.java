package br.com.postos.service.impl;

import br.com.postos.model.CotacaoCombustivel;
import br.com.postos.model.Posto;
import br.com.postos.model.enums.TipoCombustivel;
import java.util.List;

public interface PostoService {

    Posto criarPosto(Posto posto);

    List<Posto> listarTodos();

    Posto buscarPorId(Long id);

    Posto atualizarPosto(Long id, Posto posto);

    void deletarPosto(Long id);

    CotacaoCombustivel adicionarCotacao(Long postoId, CotacaoCombustivel cotacao);

    List<CotacaoCombustivel> listarCotacoesDoPosto(Long postoId);

    List<Posto> buscarPorBairro(String bairro);

    List<Posto> buscarPorCidade(String cidade);

    List<Posto> buscarPorBandeira(String bandeira);

    Double calcularPrecoMedio(TipoCombustivel tipo, String bairro);

    Posto encontrarMenorPreco(TipoCombustivel tipo, String cidade);
}