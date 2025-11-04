package com.example.demo.controller;

import com.example.demo.entity.Laboratorio;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @GetMapping
    public List<Laboratorio> getAll() {
        return laboratorioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Laboratorio getById(@PathVariable Long id) {
        return laboratorioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Laboratorio no encontrado con ID: " + id));
    }

    @PostMapping
    public Laboratorio create(@RequestBody Laboratorio lab) {
        return laboratorioRepository.save(lab);
    }

    @PutMapping("/{id}")
    public Laboratorio update(@PathVariable Long id, @RequestBody Laboratorio labDetails) {
        Laboratorio lab = laboratorioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Laboratorio no encontrado con ID: " + id));

        lab.setNombre(labDetails.getNombre());
        lab.setCapacidad(labDetails.getCapacidad());
        lab.setEstado(labDetails.getEstado());
        return laboratorioRepository.save(lab);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Laboratorio lab = laboratorioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Laboratorio no encontrado con ID: " + id));
        laboratorioRepository.delete(lab);
        return ResponseEntity.ok().build();
    }
}