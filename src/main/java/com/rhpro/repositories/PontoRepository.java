package com.rhpro.repositories;

import com.rhpro.entities.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PontoRepository extends JpaRepository<Ponto, Long> {

    @Query("SELECT p FROM Ponto p JOIN p.funcionario func WHERE func.id = :id")
    List<Ponto> pontoPorFuncionarioId(Long funcionarioId);

    @Query("SELECT p FROM Ponto p WHERE p.funcionario.id = :funcionarioId AND DATE(p.horaChegada) = :dia")
    List<Ponto> pontoPorDia(Long funcionarioId, LocalDate dia);

}
