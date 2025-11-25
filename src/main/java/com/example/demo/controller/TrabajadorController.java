package com.example.demo.controller;

import com.example.demo.entity.Trabajador;
import com.example.demo.repository.TrabajadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {

    private final TrabajadorRepository trabajadorRepository;

    public TrabajadorController(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    // Listar todos los trabajadores
    @GetMapping
    public List<Trabajador> getAll() {
        return trabajadorRepository.findAll();
    }

    // Obtener trabajador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> getById(@PathVariable Long id) {
        return trabajadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar trabajadores por laboratorio
    @GetMapping("/laboratorio/{laboratorioId}")
    public List<Trabajador> getByLaboratorio(@PathVariable Long laboratorioId) {
        return trabajadorRepository.findByLaboratorioId(laboratorioId);
    }

    // Buscar trabajadores por rol del usuario asociado
    @GetMapping("/rol/{rolId}")
    public List<Trabajador> getByRol(@PathVariable Long rolId) {
        return trabajadorRepository.findByUsuario_Rol_Id(rolId);
    }

    // Crear trabajador
    @PostMapping
    public Trabajador create(@RequestBody Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    // Actualizar trabajador
    @PutMapping("/{id}")
    public ResponseEntity<Trabajador> update(@PathVariable Long id, @RequestBody Trabajador trabajador) {
        return trabajadorRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(trabajador.getNombre());
                    existing.setApellido(trabajador.getApellido());
                    existing.setCorreo(trabajador.getCorreo());
                    existing.setTelefono(trabajador.getTelefono());
                    existing.setEstado(trabajador.getEstado());
                    existing.setLaboratorio(trabajador.getLaboratorio());
                    existing.setUsuario(trabajador.getUsuario()); // vínculo con Usuario
                    return ResponseEntity.ok(trabajadorRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar trabajador
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return trabajadorRepository.findById(id)
                .map(existing -> {
                    trabajadorRepository.delete(existing);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}