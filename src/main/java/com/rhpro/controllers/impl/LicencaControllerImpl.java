package com.rhpro.controllers.impl;

import com.rhpro.controllers.LicencaController;
import com.rhpro.dto.inputs.LicencaInput;
import com.rhpro.dto.outputs.LicencaOutput;
import com.rhpro.services.LicencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LicencaControllerImpl implements LicencaController {

    @Autowired
    private LicencaService licencaService;

    // ------------------------------------------------------------------------

    @Override
    public List<LicencaOutput> listarTodos() {
        return licencaService.listarTodos();
    }

    @Override
    public LicencaOutput retornarPorId(Long id) {
        return licencaService.retornarPorId(id);
    }

    @Override
    public void criar(LicencaInput licencaInput) {
        licencaService.criar(licencaInput);
    }

    @Override
    public void atualizar(Long id, LicencaInput licencaInput) {
        licencaService.atualizar(id, licencaInput);
    }

    @Override
    public void deletar(Long id) {
        licencaService.deletar(id);
    }
}
