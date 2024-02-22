package com.rhpro.controllers.impl;

import com.rhpro.controllers.FuncionarioController;
import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioControllerImpl implements FuncionarioController {
    @Override
    public List<FuncionarioOutputAll> listarTodos() {
        return simulandoBanco();
    }

    @Override
    public FuncionarioOutputOne retornarPorId(Long id) {
        FuncionarioOutputAll funcionarioOutputAll =  simulandoBanco()
                .stream()
                .filter(funcionario -> funcionario.getId().equals(id))
                .collect(Collectors.toList())
                .get(0);

        return FuncionarioOutputOne
                .builder()
                .id(funcionarioOutputAll.getId())
                .nome(funcionarioOutputAll.getNome())
                .sobrenome(funcionarioOutputAll.getSobrenome())
                .emailCorporativo(funcionarioOutputAll.getEmailCorporativo())
                .cpf("080.979.363-26")
                .dataDeNascimento("4 de maio de 1999")
                .salarioHora("R$ 18,00")
                .build();

    }

    @Override
    public void criar(FuncionarioInput funcionarioInput) throws IOException {
        System.out.println(funcionarioInput);
        System.out.println("SALVO COM SUCESSO :D");
    }

    @Override
    public void atualizar(Long id, FuncionarioInput funcionarioInput) throws IOException {
        System.out.println("ID: " + id);
        System.out.println(funcionarioInput);
        System.out.println("ATUALIZADO COM SUCESSO :D");
    }

    @Override
    public void deletar(Long id) {
        System.out.println("DELETADO COM SUCESSO :D");
        System.out.println("ID: " + id);
    }

    public List<FuncionarioOutputAll> simulandoBanco() {
        FuncionarioOutputAll funcionario1 = FuncionarioOutputAll.builder()
                .id(1L)
                .nome("João")
                .sobrenome("Silva")
                .emailCorporativo("joao.silva@example.com")
                .salarioHora("25.00")
                .build();

        FuncionarioOutputAll funcionario2 = FuncionarioOutputAll.builder()
                .id(2L)
                .nome("Maria")
                .sobrenome("Santos")
                .emailCorporativo("maria.santos@example.com")
                .salarioHora("30.50")
                .build();

        FuncionarioOutputAll funcionario3 = FuncionarioOutputAll.builder()
                .id(3L)
                .nome("Pedro")
                .sobrenome("Ferreira")
                .emailCorporativo("pedro.ferreira@example.com")
                .salarioHora("20.75")
                .build();

        return Arrays.asList(funcionario1, funcionario2, funcionario3);
    }

}
