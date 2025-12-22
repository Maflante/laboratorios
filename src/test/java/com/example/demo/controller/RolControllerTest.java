package com.example.demo.controller;

import com.example.demo.entity.Rol;
import com.example.demo.repository.RolRepository;
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
class RolControllerTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolController rolController;

    @Test
    void getAll_deberiaRetornarListaRoles() {
        Rol r1 = new Rol(1L, "ADMIN");
        Rol r2 = new Rol(2L, "USER");

        when(rolRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Rol> resultado = rolController.getAll();

        assertEquals(2, resultado.size());
        verify(rolRepository).findAll();
    }

    @Test
    void getById_existente_deberiaRetornar200() {
        Rol r1 = new Rol(1L, "ADMIN");
        when(rolRepository.findById(1L)).thenReturn(Optional.of(r1));

        ResponseEntity<Rol> respuesta = rolController.getById(1L);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(r1, respuesta.getBody());
    }

    @Test
    void getById_inexistente_deberiaRetornar404() {
        when(rolRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Rol> respuesta = rolController.getById(99L);

        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
    }

    @Test
    void create_deberiaGuardarYRetornarRol() {
        Rol nuevo = new Rol(null, "MANAGER");
        Rol guardado = new Rol(3L, "MANAGER");

        when(rolRepository.save(nuevo)).thenReturn(guardado);

        Rol resultado = rolController.create(nuevo);

        assertEquals(3L, resultado.getId());
        assertEquals("MANAGER", resultado.getNombre());
        verify(rolRepository).save(nuevo);
    }

    @Test
    void update_existente_deberiaActualizarRol() {
        Rol existente = new Rol(1L, "ADMIN");
        Rol detalles = new Rol(null, "SUPERADMIN");
        Rol actualizado = new Rol(1L, "SUPERADMIN");

        when(rolRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(rolRepository.save(existente)).thenReturn(actualizado);

        ResponseEntity<Rol> respuesta = rolController.update(1L, detalles);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("SUPERADMIN", respuesta.getBody().getNombre());
        verify(rolRepository).save(existente);
    }

    @Test
    void update_inexistente_deberiaRetornar404() {
        Rol detalles = new Rol(null, "SUPPORT");
        when(rolRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Rol> respuesta = rolController.update(99L, detalles);

        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    void delete_existente_deberiaEliminarYRetornar204() {
        Rol existente = new Rol(1L, "ADMIN");
        when(rolRepository.findById(1L)).thenReturn(Optional.of(existente));

        ResponseEntity<Void> respuesta = rolController.delete(1L);

        assertEquals(204, respuesta.getStatusCodeValue());
        verify(rolRepository).delete(existente);
    }

    @Test
    void delete_inexistente_deberiaRetornar404() {
        when(rolRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Void> respuesta = rolController.delete(99L);

        assertEquals(404, respuesta.getStatusCodeValue());
    }
}
