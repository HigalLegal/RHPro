package com.rhpro.dto.outputs;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicencaOutput {

    private Long id;

    private LocalDate data;

    private Image atestado;

    private String funcionario;

}
