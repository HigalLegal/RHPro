package com.rhpro.services;

import com.rhpro.dto.outputs.FolhaDePagamentoOutput;

import java.util.List;

public interface FolhaDePagamentoService {

    List<FolhaDePagamentoOutput> listarTodos();

    List<FolhaDePagamentoOutput> listarPorFuncionario(String funcionario);

    FolhaDePagamentoOutput retornarPorId(Long id);

}
