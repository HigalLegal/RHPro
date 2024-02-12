package com.rhpro.services.impl;

import com.rhpro.dto.inputs.PontoInput;
import com.rhpro.dto.outputs.PontoOutput;
import com.rhpro.repositories.PontoRepository;
import com.rhpro.services.PontoService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PontoServiceImpl implements PontoService {

    private final PontoRepository pontoRepository;

    private final ModelMapper modelMapper;

    public PontoServiceImpl(PontoRepository pontoRepository, ModelMapper modelMapper) {
        this.pontoRepository = pontoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PontoOutput> pontoPorFuncionaro(Long funcionarioId) {
        return null;
    }

    @Override
    public List<PontoOutput> pontoPorDia(Long funcionarioId, LocalDate dia) {
        return null;
    }

    @Override
    public void criar(PontoInput pontoInput) {

    }

    @Override
    public void atualizar(PontoInput pontoInput) {

    }

    @Override
    public void deletar(Long id) {

    }
}
