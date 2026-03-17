package br.com.postos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

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

    // Getters e Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }
}