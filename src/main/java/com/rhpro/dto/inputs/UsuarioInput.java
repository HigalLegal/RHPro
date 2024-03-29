package com.rhpro.dto.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioInput {

    private String email;

    private String senha;

    private Boolean admin;

    private Integer funcionarioId;

}
