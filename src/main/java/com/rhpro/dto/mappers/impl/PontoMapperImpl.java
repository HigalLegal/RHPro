package com.rhpro.dto.mappers.impl;

import com.rhpro.dto.inputs.PontoInput;
import com.rhpro.dto.mappers.PontoMapper;
import com.rhpro.dto.outputs.PontoOutput;
import com.rhpro.entities.Funcionario;
import com.rhpro.entities.Ponto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
                .data(localDateParaDataBR(entidade.getHoraChegada().toLocalDate()))
                .horaChegada(localTimeParaString(entidade.getHoraChegada().toLocalTime()))
                .horaSaida(localTimeParaString(entidade.getHoraSaida().toLocalTime()))
                .horasTrabalhadas(entidade.calcularHoraTrabalhadaPorDia())
                .funcionario(entidade.getFuncionario().getNome())
                .build();
    }

    private String localDateParaDataBR(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    private String localTimeParaString(LocalTime horario){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return horario.format(formatter);
    }

    private Funcionario gerarInstanciaFuncionario(Long id) {
        return Funcionario.builder().id(id).build();
    }
}
