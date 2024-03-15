package com.rhpro.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoOutput {

    private Long id;

    private String data;

    private String horaChegada;

    private String horaSaida;

    private BigDecimal horasTrabalhadas;

    private String funcionario;

}
