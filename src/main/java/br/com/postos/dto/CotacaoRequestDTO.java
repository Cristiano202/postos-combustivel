package br.com.postos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class CotacaoRequestDTO {

    @NotNull(message = "O tipo de combustível é obrigatório")
    private String tipo; // Será convertido para o enum TipoCombustivel no service

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private Double preco;

    private LocalDate dataColeta; // Opcional: se não informado, usaremos a data atual

    // Construtor vazio
    public CotacaoRequestDTO() {
    }

    // Construtor com todos os campos
    public CotacaoRequestDTO(String tipo, Double preco, LocalDate dataColeta) {
        this.tipo = tipo;
        this.preco = preco;
        this.dataColeta = dataColeta;
    }

}