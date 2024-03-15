package com.rhpro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "folha_de_pagamento")
public class FolhaDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "porcentagem_irf")
    private BigDecimal porcentagemIRF;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public BigDecimal calcularIRF() {
        return funcionario
                .getSalarioHora()
                .multiply(BigDecimal.valueOf(20 * 8))
                .multiply(porcentagemIRF);
    }

    public BigDecimal calcularSalarioLiquido() {
        return funcionario
                .getSalarioHora()
                .multiply(BigDecimal.valueOf(20 * 8))
                .subtract(calcularIRF());
    }

}
