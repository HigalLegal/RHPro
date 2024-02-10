package com.rhpro.dto.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoInput {

    private LocalDateTime horaChegada;

    private LocalDateTime horaSaida;

    private Integer funcionarioId;

}
