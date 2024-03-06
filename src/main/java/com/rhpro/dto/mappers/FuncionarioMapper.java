package com.rhpro.dto.mappers;

import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;
import com.rhpro.entities.Funcionario;

public interface FuncionarioMapper extends Mapper<Funcionario, FuncionarioInput, FuncionarioOutputAll> {

    FuncionarioOutputOne paraSaidaAlternativo(Funcionario funcionario);

    public static Funcionario gerarInstanciaFuncionario(Long id) {
        return FuncionarioMapper.gerarInstanciaFuncionario(id);
    }

}
