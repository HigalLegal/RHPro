package com.rhpro.repositories;

import com.rhpro.entities.FolhaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FolhaDePagamentoRepository extends JpaRepository<FolhaDePagamento, Long> {

    @Query("SELECT f FROM FolhaDePagamento f JOIN f.funcionario func WHERE func.nome = :nome")
    List<FolhaDePagamento> procurarPorNomeDoFuncionario(String nome);

    @Query("SELECT f FROM FolhaDePagamento f JOIN f.funcionario func WHERE func.id = :idFuncionario")
    FolhaDePagamento procurarPorIdDoFuncionario(Long idFuncionario);

}
