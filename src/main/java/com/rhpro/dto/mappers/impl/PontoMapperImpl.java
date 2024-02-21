package com.rhpro.dto.mappers.impl;

import com.rhpro.dto.inputs.PontoInput;
import com.rhpro.dto.mappers.PontoMapper;
import com.rhpro.dto.outputs.PontoOutput;
import com.rhpro.entities.Ponto;
import org.springframework.stereotype.Component;

import static com.rhpro.utils.GeradorFuncionario.gerarInstanciaFuncionario;

@Component
public class PontoMapperImpl implements PontoMapper {

    @Override
    public Ponto paraEntidade(PontoInput input) {
        return Ponto
                .builder()
                .horaChegada(input.getHoraChegada())
                .horaSaida(input.getHoraSaida())
                .funcionario(gerarInstanciaFuncionario(input.getFuncionarioId()))
                .build();
    }

    @Override
    public PontoOutput paraSaida(Ponto entidade) {
        return PontoOutput
                .builder()
                .id(entidade.getId())
                .data(entidade.getHoraChegada().toLocalDate())
                .horaChegada(entidade.getHoraChegada().toLocalTime())
                .horaSaida(entidade.getHoraSaida().toLocalTime())
                .horasTrabalhadas(entidade.calcularHoraTrabalhadaPorDia())
                .funcionario(entidade.getFuncionario().getNome())
                .build();
    }
}
