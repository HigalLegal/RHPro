package com.rhpro.dto.inputs;

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
public class FuncionarioInput {

    private String nome;

    private String sobrenome;

    private String emailCorporativo;

    private String cpf;

    private LocalDate dataDeNascimento;

    private BigDecimal salarioHora;

    private Long folhaDePagamentoID;

    private BigDecimal porcentagemIRF;


}

