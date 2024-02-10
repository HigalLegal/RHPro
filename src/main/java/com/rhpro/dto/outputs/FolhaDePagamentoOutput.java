package com.rhpro.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FolhaDePagamentoOutput {

    private Long id;

    private String porcentagemIRF;

    private String funcionario;

    private String valorIRF;

    private String salarioLiquido;

}
