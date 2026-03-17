package br.com.postos.model;

import java.util.ArrayList;
import java.util.List;

public class Posto {

    private Long id;
    private String cnpj;
    private String nomeFantasia;
    private String bandeira;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private List<CotacaoCombustivel> precos = new ArrayList<>();


    public Posto() {
    }


    public Posto(Long id, String cnpj, String nomeFantasia, String bandeira,
                 String endereco, String bairro, String cidade, String estado,
                 String telefone) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.bandeira = bandeira;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.precos = new ArrayList<>(); // garante que a lista não seja nula
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<CotacaoCombustivel> getPrecos() {
        return precos;
    }

    public void setPrecos(List<CotacaoCombustivel> precos) {
        this.precos = precos;
    }

    //  adicionar um preço à lista
    public void adicionarPreco(CotacaoCombustivel preco) {
        if (this.precos == null) {
            this.precos = new ArrayList<>();
        }
        this.precos.add(preco);
    }

    public void adicionarCotacao(CotacaoCombustivel cotacao) {
    }

    public List<CotacaoCombustivel> getCotacoes() {
    }
}