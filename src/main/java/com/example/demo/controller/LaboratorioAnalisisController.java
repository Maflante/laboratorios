package com.example.demo.controller;

import com.example.demo.entity.LaboratorioAnalisis;
import com.example.demo.repository.LaboratorioAnalisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioAnalisisController {

    @Autowired
    private LaboratorioAnalisisRepository relRepository;

    @PostMapping("/{labId}/analisis/{analisisId}")
    public ResponseEntity<?> asignarAnalisis(@PathVariable Long labId, @PathVariable Long analisisId) {
        LaboratorioAnalisis rel = new LaboratorioAnalisis(labId, analisisId);
        relRepository.save(rel);
        return ResponseEntity.ok().build();
    }
}