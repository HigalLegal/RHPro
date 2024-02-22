package com.rhpro.controllers.impl;

import com.rhpro.controllers.FolhaDePagamentoController;
import com.rhpro.dto.outputs.FolhaDePagamentoOutput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FolhaDePagamentoImpl implements FolhaDePagamentoController {
    @Override
    public List<FolhaDePagamentoOutput> listarTodos() {
        return simulandoBanco();
    }

    @Override
    public List<FolhaDePagamentoOutput> listarPorFuncionario(String funcionario) {
        return simulandoBanco()
                .stream()
                .filter(folha -> folha.getFuncionario().equalsIgnoreCase(funcionario))
                .collect(Collectors.toList());
    }

    @Override
    public FolhaDePagamentoOutput retornarPorId(Long id) {
        return simulandoBanco()
                .stream()
                .filter(folha -> folha.getId().equals(id))
                .collect(Collectors.toList())
                .get(0);
    }

    List<FolhaDePagamentoOutput> simulandoBanco() {
        FolhaDePagamentoOutput folha1 = FolhaDePagamentoOutput.builder()
                .id(1L)
                .porcentagemIRF("10%")
                .funcionario("João Silva")
                .valorIRF("R$ 100,00")
                .salarioLiquido("R$ 900,00")
                .build();

        FolhaDePagamentoOutput folha2 = FolhaDePagamentoOutput.builder()
                .id(2L)
                .porcentagemIRF("15%")
                .funcionario("Maria Santos")
                .valorIRF("R$ 150,00")
                .salarioLiquido("R$ 850,00")
                .build();

        FolhaDePagamentoOutput folha3 = FolhaDePagamentoOutput.builder()
                .id(3L)
                .porcentagemIRF("8%")
                .funcionario("Pedro Oliveira")
                .valorIRF("R$ 80,00")
                .salarioLiquido("R$ 920,00")
                .build();


        return Arrays.asList(folha1, folha2, folha3);
    }

}
