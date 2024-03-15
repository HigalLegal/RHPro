package com.rhpro.dto.mappers.impl;

import com.rhpro.dto.mappers.FolhaDePagamentoMapper;
import com.rhpro.dto.outputs.FolhaDePagamentoOutput;
import com.rhpro.entities.FolhaDePagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class FolhaDePagamentoMapperImpl implements FolhaDePagamentoMapper {

    @Override
    public FolhaDePagamentoOutput paraSaida(FolhaDePagamento entidade) {
        return FolhaDePagamentoOutput
                .builder()
                .id(entidade.getId())
                .porcentagemIRF(converterParaPorcentagem(entidade.getPorcentagemIRF()))
                .funcionario(entidade.getFuncionario().getNome())
                .valorIRF(converterParaReal(entidade.calcularIRF()))
                .salarioLiquido(converterParaReal(entidade.calcularSalarioLiquido()))
                .build();
    }

    private String converterParaPorcentagem(BigDecimal valor) {
        return valor
                .multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_UP)
                .toString()
                .replace(".", ",").concat("%");
    }

    private String converterParaReal(BigDecimal bigDecimal) {

        return "R$ " + bigDecimal
                .setScale(2, RoundingMode.HALF_UP)
                .toString()
                .replace(".", ",");
    }

}
