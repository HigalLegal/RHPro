package com.rhpro.dto.mappers.impl;

import com.rhpro.dto.inputs.LicencaInput;
import com.rhpro.dto.mappers.FuncionarioMapper;
import com.rhpro.dto.mappers.LicencaMapper;
import com.rhpro.dto.outputs.LicencaOutput;
import com.rhpro.entities.Funcionario;
import com.rhpro.entities.Licenca;
import javafx.scene.image.Image;
import org.springframework.stereotype.Component;

import java.io.*;



@Component
public class LicencaMapperImpl implements LicencaMapper {

    @Override
    public Licenca paraEntidade(LicencaInput input) {
        return Licenca
                .builder()
                .data(input.getData())
                .funcionario(gerarInstanciaFuncionario(input.getFuncionarioId()))
                .build();
    }

    @Override
    public LicencaOutput paraSaida(Licenca entidade) {
        return LicencaOutput
                .builder()
                .id(entidade.getId())
                .data(entidade.getData())
                .funcionario(entidade.getFuncionario().getNome())
                .build();
    }
    Funcionario gerarInstanciaFuncionario(Long id) {

        return FuncionarioMapper.gerarInstanciaFuncionario(id);
    }



}
