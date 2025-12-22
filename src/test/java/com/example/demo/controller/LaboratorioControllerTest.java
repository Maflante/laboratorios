package com.example.demo.controller;

import com.example.demo.entity.Laboratorio;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LaboratorioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaboratorioControllerTest {

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @InjectMocks
    private LaboratorioController laboratorioController;

    @Test
    void getAll_deberiaRetornarListaLaboratorios() {
        Laboratorio l1 = new Laboratorio(1L, "Lab A", 20, "Activo", LocalDate.now());
        Laboratorio l2 = new Laboratorio(2L, "Lab B", 15, "Inactivo", LocalDate.now());

        when(laboratorioRepository.findAll()).thenReturn(Arrays.asList(l1, l2));

        List<Laboratorio> resultado = laboratorioController.getAll();

        assertEquals(2, resultado.size());
        verify(laboratorioRepository).findAll();
    }

    @Test
    void getById_existente_deberiaRetornarLaboratorio() {
        Laboratorio l1 = new Laboratorio(1L, "Lab A", 20, "Activo", LocalDate.now());
        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(l1));

        Laboratorio resultado = laboratorioController.getById(1L);

        assertEquals("Lab A", resultado.getNombre());
        verify(laboratorioRepository).findById(1L);
    }

    @Test
    void getById_inexistente_deberiaLanzarExcepcion() {
        when(laboratorioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> laboratorioController.getById(99L));
    }

    @Test
    void create_deberiaGuardarYRetornarLaboratorio() {
        Laboratorio nuevo = new Laboratorio(null, "Lab C", 30, "Activo", LocalDate.now());
        Laboratorio guardado = new Laboratorio(3L, "Lab C", 30, "Activo", LocalDate.now());

        when(laboratorioRepository.save(nuevo)).thenReturn(guardado);

        Laboratorio resultado = laboratorioController.create(nuevo);

        assertEquals(3L, resultado.getId());
        assertEquals("Lab C", resultado.getNombre());
        verify(laboratorioRepository).save(nuevo);
    }

    @Test
    void update_existente_deberiaActualizarLaboratorio() {
        Laboratorio existente = new Laboratorio(1L, "Lab A", 20, "Activo", LocalDate.now());
        Laboratorio detalles = new Laboratorio(null, "Lab A+", 25, "Inactivo", LocalDate.now());
        Laboratorio actualizado = new Laboratorio(1L, "Lab A+", 25, "Inactivo", LocalDate.now());

        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(laboratorioRepository.save(existente)).thenReturn(actualizado);

        Laboratorio resultado = laboratorioController.update(1L, detalles);

        assertEquals("Lab A+", resultado.getNombre());
        assertEquals(25, resultado.getCapacidad());
        assertEquals("Inactivo", resultado.getEstado());
        verify(laboratorioRepository).save(existente);
    }

    @Test
    void update_inexistente_deberiaLanzarExcepcion() {
        Laboratorio detalles = new Laboratorio(null, "Lab X", 10, "Activo", LocalDate.now());
        when(laboratorioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> laboratorioController.update(99L, detalles));
    }

    @Test
    void delete_existente_deberiaEliminarYRetornar200() {
        Laboratorio existente = new Laboratorio(1L, "Lab A", 20, "Activo", LocalDate.now());
        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(existente));

        ResponseEntity<?> respuesta = laboratorioController.delete(1L);

        assertEquals(200, respuesta.getStatusCodeValue());
        verify(laboratorioRepository).delete(existente);
    }

    @Test
    void delete_inexistente_deberiaLanzarExcepcion() {
        when(laboratorioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> laboratorioController.delete(99L));
    }
}
