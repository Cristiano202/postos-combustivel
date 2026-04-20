package br.com.postos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "tb_posto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Posto {

    @Id
    @Column(length = 6, updatable = false, nullable = false)
    private String id; // Mudamos para String para aceitar códigos tipo "001234"

    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    private String bandeira;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;

    // Aqui a gente diz que um Posto pode ter várias Cotações
    // O 'mappedBy' aponta para o nome do campo 'posto' que vamos criar na Cotacao
    @OneToMany(mappedBy = "posto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CotacaoCombustivel> cotacoes = new ArrayList<>();

    // A mesma mágica que fizemos na outra classe: gera o ID antes de salvar
    @PrePersist
    private void gerarIdAntesDeSalvar() {
        if (this.id == null) {
            this.id = gerarCodigoAleatorio();
        }
    }

    private String gerarCodigoAleatorio() {
        Random sorteador = new Random();
        int numero = sorteador.nextInt(1000000);
        return String.format("%06d", numero);
    }

    // Um pequeno ajudante para facilitar a adição de novas cotações
    public void adicionarCotacao(CotacaoCombustivel cotacao) {
        this.cotacoes.add(cotacao);
        // cotacao.setPosto(this); // Isso aqui a gente vai usar quando configurar o outro lado
    }
}