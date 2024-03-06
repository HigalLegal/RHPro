package com.rhpro.controllers.impl;

import com.rhpro.controllers.FolhaDePagamentoController;
import com.rhpro.dto.outputs.FolhaDePagamentoOutput;
import com.rhpro.services.FolhaDePagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FolhaDePagamentoImpl implements FolhaDePagamentoController {

    @Autowired
    private FolhaDePagamentoService folhaDePagamentoService;

    // -----------------------------------------------------------------------

    @Override
    public List<FolhaDePagamentoOutput> listarTodos() {
        return folhaDePagamentoService.listarTodos();
    }

    @Override
    public List<FolhaDePagamentoOutput> listarPorFuncionario(String funcionario) {
        return folhaDePagamentoService.listarPorFuncionario(funcionario);
    }

    @Override
    public FolhaDePagamentoOutput retornarPorId(Long id) {
        return folhaDePagamentoService.retornarPorId(id);
    }
}
