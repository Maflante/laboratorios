package com.example.demo.controller;

import com.example.demo.entity.Trabajador;
import com.example.demo.entity.Laboratorio;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Rol;
import com.example.demo.repository.TrabajadorRepository;
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
class TrabajadorControllerTest {

    @Mock
    private TrabajadorRepository trabajadorRepository;

    @InjectMocks
    private TrabajadorController trabajadorController;

    @Test
    void getAll_deberiaRetornarListaTrabajadores() {
        Trabajador t1 = new Trabajador(1L, "Rodrigo", "Perez", "rodrigo@mail.com", "123456", "ACTIVO", null, null);
        Trabajador t2 = new Trabajador(2L, "Maria", "Lopez", "maria@mail.com", "987654", "INACTIVO", null, null);

        when(trabajadorRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Trabajador> resultado = trabajadorController.getAll();

        assertEquals(2, resultado.size());
        verify(trabajadorRepository).findAll();
    }

    @Test
    void getById_existente_deberiaRetornar200() {
        Trabajador t1 = new Trabajador(1L, "Rodrigo", "Perez", "rodrigo@mail.com", "123456", "ACTIVO", null, null);
        when(trabajadorRepository.findById(1L)).thenReturn(Optional.of(t1));

        ResponseEntity<Trabajador> respuesta = trabajadorController.getById(1L);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(t1, respuesta.getBody());
    }

    @Test
    void getById_inexistente_deberiaRetornar404() {
        when(trabajadorRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Trabajador> respuesta = trabajadorController.getById(99L);

        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
    }

    @Test
    void getByLaboratorio_deberiaRetornarLista() {
        Laboratorio lab = new Laboratorio(1L, "Lab A", 20, "Activo", null);
        Trabajador t1 = new Trabajador(1L, "Rodrigo", "Perez", "rodrigo@mail.com", "123456", "ACTIVO", lab, null);

        when(trabajadorRepository.findByLaboratorioId(1L)).thenReturn(Arrays.asList(t1));

        List<Trabajador> resultado = trabajadorController.getByLaboratorio(1L);

        assertEquals(1, resultado.size());
        assertEquals("Rodrigo", resultado.get(0).getNombre());
    }

    @Test
    void getByRol_deberiaRetornarLista() {
        Rol rol = new Rol(1L, "ADMIN");
        Usuario usuario = new Usuario(1L, "user1", "pass", rol);
        Trabajador t1 = new Trabajador(1L, "Rodrigo", "Perez", "rodrigo@mail.com", "123456", "ACTIVO", null, usuario);

        when(trabajadorRepository.findByUsuario_Rol_Id(1L)).thenReturn(Arrays.asList(t1));

        List<Trabajador> resultado = trabajadorController.getByRol(1L);

        assertEquals(1, resultado.size());
        assertEquals("Rodrigo", resultado.get(0).getNombre());
    }

    @Test
    void create_deberiaGuardarYRetornarTrabajador() {
        Trabajador nuevo = new Trabajador(null, "Ana", "Diaz", "ana@mail.com", "555555", "ACTIVO", null, null);
        Trabajador guardado = new Trabajador(3L, "Ana", "Diaz", "ana@mail.com", "555555", "ACTIVO", null, null);

        when(trabajadorRepository.save(nuevo)).thenReturn(guardado);

        Trabajador resultado = trabajadorController.create(nuevo);

        assertEquals(3L, resultado.getId());
        assertEquals("Ana", resultado.getNombre());
        verify(trabajadorRepository).save(nuevo);
    }

    @Test
    void update_existente_deberiaActualizarTrabajador() {
        Trabajador existente = new Trabajador(1L, "Rodrigo", "Perez", "rodrigo@mail.com", "123456", "ACTIVO", null, null);
        Trabajador detalles = new Trabajador(null, "Rodrigo", "Gomez", "rodrigo@mail.com", "999999", "INACTIVO", null, null);
        Trabajador actualizado = new Trabajador(1L, "Rodrigo", "Gomez", "rodrigo@mail.com", "999999", "INACTIVO", null, null);

        when(trabajadorRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(trabajadorRepository.save(existente)).thenReturn(actualizado);

        ResponseEntity<Trabajador> respuesta = trabajadorController.update(1L, detalles);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Gomez", respuesta.getBody().getApellido());
        assertEquals("INACTIVO", respuesta.getBody().getEstado());
        verify(trabajadorRepository).save(existente);
    }

    @Test
    void update_inexistente_deberiaRetornar404() {
        Trabajador detalles = new Trabajador(null, "Ana", "Diaz", "ana@mail.com", "555555", "ACTIVO", null, null);
        when(trabajadorRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Trabajador> respuesta = trabajadorController.update(99L, detalles);

        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    void delete_existente_deberiaEliminarYRetornar204() {
        Trabajador existente = new Trabajador(1L, "Rodrigo", "Perez", "rodrigo@mail.com", "123456", "ACTIVO", null, null);
        when(trabajadorRepository.findById(1L)).thenReturn(Optional.of(existente));

        ResponseEntity<?> respuesta = trabajadorController.delete(1L);

        assertEquals(204, respuesta.getStatusCodeValue());
        verify(trabajadorRepository).delete(existente);
    }

    @Test
    void delete_inexistente_deberiaRetornar404() {
        when(trabajadorRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<?> respuesta = trabajadorController.delete(99L);

        assertEquals(404, respuesta.getStatusCodeValue());
    }
}
