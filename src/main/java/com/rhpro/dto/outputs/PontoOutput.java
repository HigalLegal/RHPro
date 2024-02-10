package com.rhpro.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoOutput {

    private Long id;

    private LocalDate data;

    private LocalTime horaChegada;

    private LocalTime horaSaida;

    private double horasTrabalhadas;

    private String funcionario;

}
