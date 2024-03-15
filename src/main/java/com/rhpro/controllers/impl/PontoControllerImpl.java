package com.rhpro.controllers.impl;

import com.rhpro.controllers.PontoController;
import com.rhpro.dto.inputs.PontoInput;
import com.rhpro.dto.outputs.PontoOutput;
import com.rhpro.services.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PontoControllerImpl implements PontoController {

    @Autowired
    private PontoService pontoService;

    // -----------------------------------------------------------------------------------------

    @Override
    public List<PontoOutput> todosOsPontos() {
        return pontoService.listarPontos();
    }

    @Override
    public List<PontoOutput> pontoPorFuncionaro(Long funcionarioId) {
        return pontoService.pontoPorFuncionaro(funcionarioId);
    }

    @Override
    public List<PontoOutput> pontoPorDia(Long funcionarioId, LocalDate dia) {
        return pontoService.pontoPorDia(funcionarioId, dia);
    }

    @Override
    public void criar(PontoInput pontoInput) {
        System.out.println(pontoInput);
        pontoService.criar(pontoInput);
    }

    @Override
    public void atualizar(Long id, PontoInput pontoInput) {
        pontoService.atualizar(id, pontoInput);
    }

    @Override
    public void deletar(Long id) {
        pontoService.deletar(id);
    }
}
