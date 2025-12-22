package com.example.demo.controller;

import com.example.demo.entity.TiposAnalisis;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TiposAnalisisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TiposAnalisisControllerTest {

    @Mock
    private TiposAnalisisRepository analisisRepository;

    @InjectMocks
    private TiposAnalisisController tiposAnalisisController;

    @Test
    void getAll_deberiaRetornarListaAnalisis() {
        TiposAnalisis a1 = new TiposAnalisis(1L, "Sangre", "Análisis de sangre");
        TiposAnalisis a2 = new TiposAnalisis(2L, "Orina", "Análisis de orina");

        when(analisisRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<TiposAnalisis> resultado = tiposAnalisisController.getAll();

        assertEquals(2, resultado.size());
        verify(analisisRepository).findAll();
    }

    @Test
    void getById_existente_deberiaRetornarAnalisis() {
        TiposAnalisis a1 = new TiposAnalisis(1L, "Sangre", "Análisis de sangre");
        when(analisisRepository.findById(1L)).thenReturn(Optional.of(a1));

        TiposAnalisis resultado = tiposAnalisisController.getById(1L);

        assertEquals("Sangre", resultado.getNombre());
        verify(analisisRepository).findById(1L);
    }

    @Test
    void getById_inexistente_deberiaLanzarExcepcion() {
        when(analisisRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tiposAnalisisController.getById(99L));
    }

    @Test
    void create_deberiaGuardarYRetornarAnalisis() {
        TiposAnalisis nuevo = new TiposAnalisis(null, "Glucosa", "Análisis de glucosa");
        TiposAnalisis guardado = new TiposAnalisis(3L, "Glucosa", "Análisis de glucosa");

        when(analisisRepository.save(nuevo)).thenReturn(guardado);

        TiposAnalisis resultado = tiposAnalisisController.create(nuevo);

        assertEquals(3L, resultado.getId());
        assertEquals("Glucosa", resultado.getNombre());
        verify(analisisRepository).save(nuevo);
    }

    @Test
    void update_existente_deberiaActualizarAnalisis() {
        TiposAnalisis existente = new TiposAnalisis(1L, "Sangre", "Análisis básico");
        TiposAnalisis detalles = new TiposAnalisis(null, "Sangre avanzada", "Análisis completo");
        TiposAnalisis actualizado = new TiposAnalisis(1L, "Sangre avanzada", "Análisis completo");

        when(analisisRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(analisisRepository.save(existente)).thenReturn(actualizado);

        TiposAnalisis resultado = tiposAnalisisController.update(1L, detalles);

        assertEquals("Sangre avanzada", resultado.getNombre());
        assertEquals("Análisis completo", resultado.getDescripcion());
        verify(analisisRepository).save(existente);
    }

    @Test
    void update_inexistente_deberiaLanzarExcepcion() {
        TiposAnalisis detalles = new TiposAnalisis(null, "Orina", "Análisis de orina");
        when(analisisRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tiposAnalisisController.update(99L, detalles));
    }

    @Test
    void delete_existente_deberiaEliminarYRetornar200() {
        TiposAnalisis existente = new TiposAnalisis(1L, "Sangre", "Análisis básico");
        when(analisisRepository.findById(1L)).thenReturn(Optional.of(existente));

        ResponseEntity<?> respuesta = tiposAnalisisController.delete(1L);

        assertEquals(200, respuesta.getStatusCodeValue());
        verify(analisisRepository).delete(existente);
    }

    @Test
    void delete_inexistente_deberiaLanzarExcepcion() {
        when(analisisRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tiposAnalisisController.delete(99L));
    }
}
