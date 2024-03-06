package com.rhpro.services;

import com.rhpro.dto.inputs.FuncionarioInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class FuncionarioServiceTest {

    @Autowired
    private FuncionarioService funcionarioService;

    // ---------------------------------------------------------------------

    public void listarTodos() {}

    public void retornarPorId() {}

    @Test
    public void criar() {
        funcionarioService.criar(instanciarFuncionario());
    }

    public void atualizar() {}

    public void deletar() {}

    private FuncionarioInput instanciarFuncionario() {
        FuncionarioInput funcionarioInput = FuncionarioInput.builder()
                .nome("Pedro")
                .sobrenome("dos Bode")
                .emailCorporativo("bode@example.com")
                .cpf("123.456.789-00")
                .dataDeNascimento(LocalDate.of(1990, 1, 1))
                .salarioHora(new BigDecimal("50.00"))
                .folhaDePagamentoID(1L)
                .porcentagemIRF(new BigDecimal("10.00"))
                .build();

        return funcionarioInput;

    }

}
