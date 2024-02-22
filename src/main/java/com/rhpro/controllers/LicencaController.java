package com.rhpro.controllers;

import com.rhpro.dto.inputs.LicencaInput;
import com.rhpro.dto.outputs.LicencaOutput;

import java.util.List;

public interface LicencaController {

    List<LicencaOutput> listarTodos();

    LicencaOutput retornarPorId(Long id);

    void criar(LicencaInput licencaInput);

    void atualizar(Long id, LicencaInput licencaInput);

    void deletar(Long id);

}
