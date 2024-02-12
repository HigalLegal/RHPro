package com.rhpro.services;

import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;

import java.util.List;

public interface FuncionarioService {

    List<FuncionarioOutputAll> listarTodos();

    FuncionarioOutputOne retornarPorId(Long id);

    void criar(FuncionarioInput funcionarioInput);

    void atualizar(Long id, FuncionarioInput funcionarioInput);

    void deletar(Long id);

}
