package com.rhpro.dto.outputs;

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
public class LicencaOutput {

    private Long id;

    private LocalDate data;

    //é pra ser uma imagem...
    private File atestado;

    private String funcionario;

}
