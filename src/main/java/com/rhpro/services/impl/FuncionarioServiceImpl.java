package com.rhpro.services.impl;

import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;
import com.rhpro.entities.Funcionario;
import com.rhpro.repositories.FuncionarioRepository;
import com.rhpro.services.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private final ModelMapper modelMapper;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository,
                                  ModelMapper modelMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FuncionarioOutputAll> listarTodos() {
        return funcionarioRepository
                .findAll()
                .stream()
                .map(funcionario -> modelMapper.map(funcionario, FuncionarioOutputAll.class))
                .collect(Collectors.toList());
    }

    @Override
    public FuncionarioOutputOne retornarPorId(Long id) {

        Funcionario funcionario = funcionarioRepository.findById(id).get();

        if(funcionario == null) {
            throw new EntityNotFoundException("Registro inexistente");
        }

        return modelMapper.map(funcionario, FuncionarioOutputOne.class);
    }

    @Override
    public void criar(FuncionarioInput funcionarioInput) {
        Funcionario funcionario = modelMapper.map(funcionarioInput, Funcionario.class);

        funcionarioRepository.save(funcionario);
    }

    @Override
    public void atualizar(Long id, FuncionarioInput funcionarioInput) {

        boolean existente = funcionarioRepository.existsById(id);

        if(!existente) {
            throw new EntityNotFoundException("Registro inexistente");
        }


        Funcionario funcionario = modelMapper.map(funcionarioInput, Funcionario.class);
        funcionario.setId(id);

        funcionarioRepository.save(funcionario);

    }

    @Override
    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
