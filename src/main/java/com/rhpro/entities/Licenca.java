package com.rhpro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Licenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "data")
    private LocalDate data;

    @Column (nullable = false, name = "atestado")
    @Lob
    private byte[] atestado;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}
