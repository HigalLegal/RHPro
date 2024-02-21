package com.rhpro.services.impl;

import com.rhpro.dto.mappers.FolhaDePagamentoMapper;
import com.rhpro.dto.outputs.FolhaDePagamentoOutput;
import com.rhpro.entities.FolhaDePagamento;
import com.rhpro.repositories.FolhaDePagamentoRepository;
import com.rhpro.services.FolhaDePagamentoService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class FolhaDePagamentoServiceImpl implements FolhaDePagamentoService {

    private final FolhaDePagamentoRepository folhaDePagamentoRepository;

    private final FolhaDePagamentoMapper folhaDePagamentoMapper;

    public FolhaDePagamentoServiceImpl(FolhaDePagamentoRepository folhaDePagamentoRepository,
                                  FolhaDePagamentoMapper folhaDePagamentoMapper) {
        this.folhaDePagamentoRepository = folhaDePagamentoRepository;
        this.folhaDePagamentoMapper = folhaDePagamentoMapper;
    }


    @Override
    public List<FolhaDePagamentoOutput> listarTodos() {
        return folhaDePagamentoRepository
                .findAll()
                .stream()
                .map(folhaDePagamentoMapper::paraSaida)
                .collect(Collectors.toList());
    }

    @Override
    public List<FolhaDePagamentoOutput> listarPorFuncionario(String funcionario) {
        return folhaDePagamentoRepository
                .procurarPorNomeDoFuncionario(funcionario)
                .stream()
                .map(folhaDePagamentoMapper::paraSaida)
                .collect(Collectors.toList());
    }

    @Override
    public FolhaDePagamentoOutput retornarPorId(Long id) {
        FolhaDePagamento folhaDePagamento = folhaDePagamentoRepository.findById(id).get();

        if(folhaDePagamento == null) {
            throw new EntityNotFoundException("Registro inexistente");
        }

        return folhaDePagamentoMapper.paraSaida(folhaDePagamento);

    }
}
