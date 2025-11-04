package com.example.demo.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratorioAnalisisId implements Serializable {
    private Long laboratorioId;
    private Long analisisId;
}