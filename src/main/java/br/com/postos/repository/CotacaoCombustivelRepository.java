package br.com.postos.repository;

import br.com.postos.model.CotacaoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CotacaoCombustivelRepository extends JpaRepository<CotacaoCombustivel, String> {
    // Busca todas as cotações que pertencem a um posto específico
    List<CotacaoCombustivel> findByPostoId(String postoId);
}