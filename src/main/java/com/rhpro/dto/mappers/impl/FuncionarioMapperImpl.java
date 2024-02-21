package com.rhpro.dto.mappers.impl;

import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.mappers.FuncionarioMapper;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;
import com.rhpro.entities.FolhaDePagamento;
import com.rhpro.entities.Funcionario;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class FuncionarioMapperImpl implements FuncionarioMapper {


    @Override
    public FuncionarioOutputOne paraSaidaAlternativo(Funcionario funcionario) {
        return FuncionarioOutputOne
                .builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .sobrenome(funcionario.getSobrenome())
                .emailCorporativo(funcionario.getEmailCorporativo())
                .cpf(funcionario.getCpf())
                .dataDeNascimento(dataParaString(funcionario.getDataDeNascimento()))
                .salarioHora(bigDecimalParaReal(funcionario.getSalarioHora()))
                .build();
    }

    @Override
    public Funcionario paraEntidade(FuncionarioInput input) {
        return Funcionario
                .builder()
                .nome(input.getNome())
                .sobrenome(input.getSobrenome())
                .emailCorporativo(input.getEmailCorporativo())
                .cpf(input.getCpf())
                .dataDeNascimento(input.getDataDeNascimento())
                .salarioHora(input.getSalarioHora())
                .folhaDePagamento(gerarFolhaDePagamento(input.getFolhaDePagamentoID(),
                                                        input.getPorcentagemIRF()))
                .build();
    }

    @Override
    public FuncionarioOutputAll paraSaida(Funcionario entidade) {
        return FuncionarioOutputAll
                .builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .sobrenome(entidade.getSobrenome())
                .emailCorporativo(entidade.getEmailCorporativo())
                .build();
    }

    private String dataParaString(LocalDate data) {

        int dia = data.getDayOfMonth();
        String mes = extrairMes(data);
        int ano = data.getYear();

        return dia + " de " + mes + " de " + ano;
    }

    private String extrairMes(LocalDate data) {

        int numeroDoMes = data.getMonthValue();

        String mesString = "janeiro";

        switch (numeroDoMes) {
            case 2 -> mesString = "fevereiro";
            case 3 -> mesString = "março";
            case 4 -> mesString = "abril";
            case 5 -> mesString = "maio";
            case 6 -> mesString = "junho";
            case 7 -> mesString = "julho";
            case 8 -> mesString = "agosto";
            case 9 -> mesString = "setembro";
            case 10 -> mesString = "outubro";
            case 11 -> mesString = "novembro";
            case 12 -> mesString = "dezembro";
        }

        return mesString;
    }

    private FolhaDePagamento gerarFolhaDePagamento(Long id, BigDecimal porcentagemIRF) {
        return FolhaDePagamento
                .builder()
                .id(id)
                .porcentagemIRF(porcentagemIRF)
                .build();
    }

    private String bigDecimalParaReal(BigDecimal valor) {
        return valor.toString().replace(".", ",");
    }


}
