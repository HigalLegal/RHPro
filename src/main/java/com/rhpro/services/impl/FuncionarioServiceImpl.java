package com.rhpro.services.impl;

import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.mappers.FuncionarioMapper;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;
import com.rhpro.entities.Funcionario;
import com.rhpro.repositories.FuncionarioRepository;
import com.rhpro.services.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioMapper funcionarioMapper;

    @Override
    public List<FuncionarioOutputAll> listarTodos() {
        return funcionarioRepository
                .findAll()
                .stream()
                .map(funcionarioMapper::paraSaida)
                .collect(Collectors.toList());
    }

    @Override
    public FuncionarioOutputOne retornarPorId(Long id) {

        Funcionario funcionario = funcionarioRepository.findById(id).get();

        if(funcionario == null) {
            throw new EntityNotFoundException("Registro inexistente");
        }

        return funcionarioMapper.paraSaidaAlternativo(funcionario);
    }

    @Override
    @Transactional
    public void criar(FuncionarioInput funcionarioInput)  {
        Funcionario funcionario = funcionarioMapper.paraEntidade(funcionarioInput);

        funcionarioRepository.save(funcionario);
    }

    @Override
    @Transactional
    public void atualizar(Long id, FuncionarioInput funcionarioInput) {

        boolean existente = funcionarioRepository.existsById(id);

        if(!existente) {
            throw new EntityNotFoundException("Registro inexistente");
        }

        Funcionario funcionario = funcionarioMapper.paraEntidade(funcionarioInput);
        funcionario.setId(id);

        funcionarioRepository.save(funcionario);

    }

    @Override
    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }

//    private Long idFolha(Long idFuncionario) {
//        return funcionarioRepository
//                .findById(idFuncionario)
//                .get()
//                .getFolhaDePagamento()
//                .getId();
//    }
}
