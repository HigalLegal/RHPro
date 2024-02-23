package com.rhpro.services.impl;

import com.rhpro.dto.inputs.LicencaInput;
import com.rhpro.dto.mappers.LicencaMapper;
import com.rhpro.dto.outputs.LicencaOutput;
import com.rhpro.entities.Licenca;
import com.rhpro.repositories.LicencaRepository;
import com.rhpro.services.LicencaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicencaServiceImpl implements LicencaService {

    private final LicencaRepository licencaRepository;

    private final LicencaMapper licencaMapper;

    public LicencaServiceImpl(LicencaRepository licencaRepository,
                              LicencaMapper licencaMapper) {
        this.licencaRepository = licencaRepository;
        this.licencaMapper = licencaMapper;
    }

    @Override
    public List<LicencaOutput> listarTodos() {
        return licencaRepository
                .findAll()
                .stream()
                .map(licencaMapper::paraSaida)
                .collect(Collectors.toList());
    }

    @Override
    public LicencaOutput retornarPorId(Long id) {

        Licenca licenca = licencaRepository.findById(id).get();

        if(licenca == null) {
            throw new EntityNotFoundException("Registro inexistente");
        }

        return licencaMapper.paraSaida(licenca);
    }

    @Override
    public void criar(LicencaInput licencaInput) {
        licencaRepository.save(licencaMapper.paraEntidade(licencaInput));
    }

    @Override
    public void atualizar(Long id, LicencaInput licencaInput) {

        boolean existente = licencaRepository.existsById(id);

        if(!existente) {
            throw new EntityNotFoundException("Registro inexistente");
        }

        Licenca licenca = licencaMapper.paraEntidade(licencaInput);
        licenca.setId(id);

        licencaRepository.save(licenca);
    }

    @Override
    public void deletar(Long id) {
        boolean existente = licencaRepository.existsById(id);

        if(!existente) {
            throw new EntityNotFoundException("Registro inexistente");
        }

        licencaRepository.deleteById(id);
    }
}
