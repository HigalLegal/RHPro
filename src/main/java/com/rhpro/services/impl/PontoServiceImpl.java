package com.rhpro.services.impl;

import com.rhpro.dto.inputs.PontoInput;
import com.rhpro.dto.mappers.PontoMapper;
import com.rhpro.dto.outputs.PontoOutput;
import com.rhpro.entities.Ponto;
import com.rhpro.repositories.PontoRepository;
import com.rhpro.services.PontoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoServiceImpl implements PontoService {

    private final PontoRepository pontoRepository;

    private final PontoMapper pontoMapper;

    public PontoServiceImpl(PontoRepository pontoRepository, PontoMapper pontoMapper) {
        this.pontoRepository = pontoRepository;
        this.pontoMapper = pontoMapper;
    }

    @Override
    public List<PontoOutput> pontoPorFuncionaro(Long funcionarioId) {
        return pontoRepository
                //.pontoPorFuncionarioId(funcionarioId)
                .findAll()
                .stream()
                .map(pontoMapper::paraSaida)
                .collect(Collectors.toList());
    }

    @Override
    public List<PontoOutput> pontoPorDia(Long funcionarioId, LocalDate dia) {
        return pontoRepository
                //.pontoPorDia(funcionarioId, dia)
                .findAll()
                .stream()
                .map(pontoMapper::paraSaida)
                .collect(Collectors.toList());
    }

    @Override
    public void criar(PontoInput pontoInput) {
        pontoRepository.save(pontoMapper.paraEntidade(pontoInput));
    }

    @Override
    public void atualizar(Long id, PontoInput pontoInput) {

        boolean existente = pontoRepository.existsById(id);

        if(!existente) {
            throw new EntityNotFoundException("Registro inexistente");
        }

        Ponto ponto = pontoMapper.paraEntidade(pontoInput);
        ponto.setId(id);

        pontoRepository.save(ponto);
    }

    @Override
    public void deletar(Long id) {
        pontoRepository.deleteById(id);
    }
}
