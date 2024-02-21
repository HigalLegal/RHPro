package com.rhpro.dto.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicencaInput {

    private LocalDate data;

    //é pra ser uma imagem...
    private File atestado;

    private Long funcionarioId;
}
