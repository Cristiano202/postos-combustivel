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

    // ---------- Posto ----------
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
        // A lista de cotações não é mapeada aqui, pois é criada separadamente
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

        // Mapeia a lista de cotações, se existir
        if (entity.getCotacoes() != null) {
            dto.setCotacoes(
                    entity.getCotacoes().stream()
                            .map(PostoMapper::toResponseDTO)
                            .collect(Collectors.toList())
            );
        } else {
            dto.setCotacoes(Collections.emptyList());
        }
        return dto;
    }

    // ---------- Cotação ----------
    public static CotacaoCombustivel toEntity(CotacaoRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        CotacaoCombustivel cotacao = new CotacaoCombustivel();
        // Converte a String do tipo para o Enum (case insensitive)
        if (dto.getTipo() != null) {
            cotacao.setTipo(TipoCombustivel.valueOf(dto.getTipo().toUpperCase()));
        }
        cotacao.setPreco(dto.getPreco());
        cotacao.setDataColeta(dto.getDataColeta());
        // ID será gerado no service/repository
        return cotacao;
    }

    public static CotacaoResponseDTO toResponseDTO(CotacaoCombustivel entity) {
        if (entity == null) {
            return null;
        }
        CotacaoResponseDTO dto = new CotacaoResponseDTO();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo() != null ? entity.getTipo().name() : null);
        dto.setPreco(entity.getPreco());
        dto.setDataColeta(entity.getDataColeta());
        return dto;
    }
}