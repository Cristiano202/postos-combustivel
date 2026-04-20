package br.com.postos.model;

import br.com.postos.model.enums.TipoCombustivel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Table(name = "tb_cotacao_combustivel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "posto")
public class CotacaoCombustivel {

    @Id
    @Column(length = 6, updatable = false, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCombustivel tipo;

    @Column(nullable = false)
    private Double preco;

    @Column(name = "data_coleta", nullable = false)
    private LocalDate dataColeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posto_id", nullable = false)
    private Posto posto;

    @PrePersist
    private void prepararParaSalvar() {
        if (this.id == null) {
            this.id = criarCodigoAleatorio();
        }
    }

    private String criarCodigoAleatorio() {
        Random sorteador = new Random();
        return String.format("%06d", sorteador.nextInt(1000000));
    }
}