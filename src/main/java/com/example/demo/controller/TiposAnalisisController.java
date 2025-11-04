package com.example.demo.controller;

import com.example.demo.entity.TiposAnalisis;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TiposAnalisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analisis")
public class TiposAnalisisController {

    @Autowired
    private TiposAnalisisRepository analisisRepository;

    @GetMapping
    public List<TiposAnalisis> getAll() {
        return analisisRepository.findAll();
    }

    @GetMapping("/{id}")
    public TiposAnalisis getById(@PathVariable Long id) {
        return analisisRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Análisis no encontrado con ID: " + id));
    }

    @PostMapping
    public TiposAnalisis create(@RequestBody TiposAnalisis analisis) {
        return analisisRepository.save(analisis);
    }

    @PutMapping("/{id}")
    public TiposAnalisis update(@PathVariable Long id, @RequestBody TiposAnalisis datos) {
        TiposAnalisis a = analisisRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Análisis no encontrado"));
        a.setNombre(datos.getNombre());
        a.setDescripcion(datos.getDescripcion());
        return analisisRepository.save(a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        TiposAnalisis a = analisisRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Análisis no encontrado"));
        analisisRepository.delete(a);
        return ResponseEntity.ok().build();
    }
}