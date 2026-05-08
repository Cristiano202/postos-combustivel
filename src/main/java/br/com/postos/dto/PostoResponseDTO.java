package br.com.postos.dto;

import java.util.List;

public class PostoResponseDTO {

    private String id;
    private String cnpj;
    private String nomeFantasia;
    private String bandeira;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private List<CotacaoResponseDTO> cotacoes;

    // Construtor vazio (obrigatório para serialização)
    public PostoResponseDTO() {
    }

    // Construtor com todos os campos (útil para criar o DTO rapidamente)
    public PostoResponseDTO(String id, String cnpj, String nomeFantasia, String bandeira,
                            String endereco, String bairro, String cidade, String estado,
                            String telefone, List<CotacaoResponseDTO> cotacoes) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.bandeira = bandeira;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.cotacoes = cotacoes;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<CotacaoResponseDTO> getCotacoes() {
        return cotacoes;
    }

    public void setCotacoes(List<CotacaoResponseDTO> cotacoes) {
        this.cotacoes = cotacoes;
    }
}