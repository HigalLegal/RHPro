package com.rhpro.dto.mappers.impl;

import com.rhpro.dto.mappers.FolhaDePagamentoMapper;
import com.rhpro.dto.outputs.FolhaDePagamentoOutput;
import com.rhpro.entities.FolhaDePagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FolhaDePagamentoMapperImpl implements FolhaDePagamentoMapper {

    @Override
    public FolhaDePagamentoOutput paraSaida(FolhaDePagamento entidade) {
        return FolhaDePagamentoOutput
                .builder()
                .id(entidade.getId())
                .porcentagemIRF(converterParaReal(entidade.getPorcentagemIRF()))
                .funcionario(entidade.getFuncionario().getNome())
                .valorIRF(converterParaReal(entidade.calcularIRF()))
                .salarioLiquido(converterParaReal(entidade.calcularSalarioLiquido()))
                .build();
    }

    private String converterParaReal(BigDecimal bigDecimal) {

        String valor = bigDecimal.toString();

        String valorComVirgula = valor.replaceAll(".", ",");

        return "R$ " + valorComVirgula;
    }

}
