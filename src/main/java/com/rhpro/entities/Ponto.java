package com.rhpro.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "hora_chegada")
    private LocalDateTime horaChegada;

    @Column(nullable = false, name = "hora_saida")
    private LocalDateTime horaSaida;

    @ManyToOne
    private Funcionario funcionario;

    public BigDecimal calcularHoraTrabalhadaPorDia() {
        Duration duration = Duration.between(horaChegada, horaSaida);
        BigDecimal segundosTrabalhados = BigDecimal
                .valueOf(duration.toSeconds());

        return segundosTrabalhados
                .divide(BigDecimal.valueOf(3600.0),
                        2, RoundingMode.HALF_DOWN);
    }
}
