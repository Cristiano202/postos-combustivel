package br.com.postos.mapper;

import br.com.postos.dto.CotacaoRequestDTO;
import br.com.postos.dto.CotacaoResponseDTO;
import br.com.postos.dto.PostoRequestDTO;
import br.com.postos.dto.PostoResponseDTO;
import br.com.postos.model.CotacaoCombustivel;
import br.com.postos.model.Posto;
import br.com.postos.model.enums.TipoCombustivel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostoMapper {

    // ---------- Mapeamento de Posto ----------
    public static Posto toEntity(PostoRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Posto posto = new Posto();
        posto.setCnpj(dto.getCnpj());
        posto.setNomeFantasia(dto.getNomeFantasia());
        posto.setBandeira(dto.getBandeira());
        posto.setEndereco(dto.getEndereco());
        posto.setBairro(dto.getBairro());
        posto.setCidade(dto.getCidade());
        posto.setEstado(dto.getEstado());
        posto.setTelefone(dto.getTelefone());
        return posto;
    }

    public static PostoResponseDTO toResponseDTO(Posto entity) {
        if (entity == null) {
            return null;
        }
        PostoResponseDTO dto = new PostoResponseDTO();
        dto.setId(entity.getId());
        dto.setCnpj(entity.getCnpj());
        dto.setNomeFantasia(entity.getNomeFantasia());
        dto.setBandeira(entity.getBandeira());
        dto.setEndereco(entity.getEndereco());
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setTelefone(entity.getTelefone());

        // CORREÇÃO: Usando a lambda para garantir que chame o toResponseDTO de Cotação
        if (entity.getCotacoes() != null) {
            dto.setCotacoes(
                    entity.getCotacoes().stream()
                            .map(cotacao -> toResponseDTO(cotacao))
                            .collect(Collectors.toList())
            );
        } else {
            dto.setCotacoes(Collections.emptyList());
        }
        return dto;
    }

    // ---------- Mapeamento de Cotação ----------
    public static CotacaoCombustivel toEntity(CotacaoRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        CotacaoCombustivel cotacao = new CotacaoCombustivel();
        if (dto.getTipo() != null) {
            // Converte String para Enum
            cotacao.setTipo(TipoCombustivel.valueOf(dto.getTipo().toUpperCase()));
        }
        cotacao.setPreco(dto.getPreco());
        cotacao.setDataColeta(dto.getDataColeta());
        return cotacao;
    }

    public static CotacaoResponseDTO toResponseDTO(CotacaoCombustivel entity) {
        if (entity == null) {
            return null;
        }
        CotacaoResponseDTO dto = new CotacaoResponseDTO();
        dto.setId(Long.valueOf(entity.getId()));
        dto.setTipo(entity.getTipo() != null ? entity.getTipo().name() : null);
        dto.setPreco(entity.getPreco());
        dto.setDataColeta(entity.getDataColeta());
        return dto;
    }
}