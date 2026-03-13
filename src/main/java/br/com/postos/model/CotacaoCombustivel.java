package br.com.postos.model;

import br.com.postos.model.enums.TipoCombustivel;

import java.time.LocalDate;

public class CotacaoCombustivel {
    private long id;
    private TipoCombustivel tipo;
    private double preco;
    private LocalDate dataColeta;

    public CotacaoCombustivel() {

    }

    public CotacaoCombustivel(long id, TipoCombustivel tipo, double preco, LocalDate dataColeta) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
        this.dataColeta = dataColeta;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoCombustivel getTipo() {
        return tipo;
    }

    public void setTipo(TipoCombustivel tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    @Override
    public String toString() {
        return "CotacaoCombustivel{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", preco=" + preco +
                ", dataColeta=" + dataColeta +
                '}';
    }
}
