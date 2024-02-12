package com.rhpro.services;

import com.rhpro.dto.inputs.PontoInput;
import com.rhpro.dto.outputs.PontoOutput;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PontoService {

    List<PontoOutput> pontoPorFuncionaro(Long funcionarioId);

    List<PontoOutput> pontoPorDia(Long funcionarioId, LocalDate dia);

    void criar(PontoInput pontoInput);

    void atualizar(PontoInput pontoInput);

    void deletar(Long id);
    
}
