package com.rhpro.utils;

import com.rhpro.entities.Funcionario;

public class GeradorFuncionario {

    public static Funcionario gerarInstanciaFuncionario(Long id) {
        return Funcionario.builder().id(id).build();
    }

}
