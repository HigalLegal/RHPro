package com.rhpro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false, name = "email_corporativo")
    private String emailCorporativo;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false, name = "data_de_nascimento")
    private LocalDate dataDeNascimento;

    @Column(nullable = false, name = "salario_por_hora")
    private BigDecimal salarioHora;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "folha_de_pagamento_id")
    private FolhaDePagamento folhaDePagamento;

}
