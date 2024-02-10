package com.rhpro.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioOutputOne {

    private Long id;

    private String nome;

    private String sobrenome;

    private String emailCorporativo;

    private String cpf;

    private String dataDeNascimento;

    private String salarioHora;


}
