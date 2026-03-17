package br.com.postos.dto;

import java.time.LocalDate;

public class CotacaoResponseDTO {

    private Long id;
    private String tipo;        // Nome do combustível (ex: "GASOLINA")
    private Double preco;
    private LocalDate dataColeta;

    // Construtor vazio
    public CotacaoResponseDTO() {
    }

    // Construtor com todos os campos
    public CotacaoResponseDTO(Long id, String tipo, Double preco, LocalDate dataColeta) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
        this.dataColeta = dataColeta;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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