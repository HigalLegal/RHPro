package com.rhpro.controllers.impl;

import com.rhpro.controllers.FuncionarioController;
import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;
import com.rhpro.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FuncionarioControllerImpl implements FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // -----------------------------------------------------------------------

    @Override
    public List<FuncionarioOutputAll> listarTodos() {
        return funcionarioService.listarTodos();
    }

    @Override
    public FuncionarioOutputOne retornarPorId(Long id) {
        return funcionarioService.retornarPorId(id);
    }

    @Override
    public void criar(FuncionarioInput funcionarioInput) {
        System.out.println(funcionarioInput);
        funcionarioService.criar(funcionarioInput);
    }

    @Override
    public void atualizar(Long id, FuncionarioInput funcionarioInput) {
        funcionarioService.atualizar(id, funcionarioInput);
    }

    @Override
    public void deletar(Long id) {
        funcionarioService.deletar(id);
    }
}
