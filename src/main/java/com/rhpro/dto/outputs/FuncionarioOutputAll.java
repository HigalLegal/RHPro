package com.rhpro.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioOutputAll {

    private Long id;

    private String nome;

    private String sobrenome;

    private String emailCorporativo;

    private String salarioHora;

}
