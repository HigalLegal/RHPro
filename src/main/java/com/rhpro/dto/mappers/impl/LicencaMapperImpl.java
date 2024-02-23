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
                .atestado(filetoByteArray(input.getAtestado()))
                .funcionario(gerarInstanciaFuncionario(input.getFuncionarioId()))
                .build();
    }

    @Override
    public LicencaOutput paraSaida(Licenca entidade) {
        return LicencaOutput
                .builder()
                .id(entidade.getId())
                .data(entidade.getData())
                .atestado(convertBytesToImage(entidade.getAtestado()))
                .funcionario(entidade.getFuncionario().getNome())
                .build();
    }
    Funcionario gerarInstanciaFuncionario(Long id) {

        return FuncionarioMapper.gerarInstanciaFuncionario(id);
    }
    private byte[] filetoByteArray(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] fileBytes = new byte[(int) file.length()];
            fis.read(fileBytes);
            fis.close();
            return fileBytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Image convertBytesToImage(byte[] bytes) {
        try {
            // Cria um ByteArrayInputStream com os bytes
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

            // Cria e retorna um objeto Image do JavaFX com base nos bytes
            return new Image(bis);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
