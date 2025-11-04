package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "laboratorio_analisis")
@IdClass(LaboratorioAnalisisId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratorioAnalisis {

    @Id
    @Column(name = "laboratorio_id")
    private Long laboratorioId;

    @Id
    @Column(name = "analisis_id")
    private Long analisisId;
}
