package com.rhpro.controllers;

import com.rhpro.dto.outputs.FolhaDePagamentoOutput;

import java.util.List;

public interface FolhaDePagamentoController {

    List<FolhaDePagamentoOutput> listarTodos();

    List<FolhaDePagamentoOutput> listarPorFuncionario(String funcionario);

    FolhaDePagamentoOutput retornarPorId(Long id);

}
