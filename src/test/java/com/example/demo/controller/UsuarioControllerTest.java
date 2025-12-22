package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Rol;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void getAllUsuarios_deberiaRetornarListaUsuarios() {
        Rol rolAdmin = new Rol(1L, "ADMIN");
        Rol rolUser = new Rol(2L, "USER");

        Usuario u1 = new Usuario(1L, "Rodrigo", "pass", rolAdmin);
        Usuario u2 = new Usuario(2L, "Maria", "pass", rolUser);

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuario> resultado = usuarioController.getAllUsuarios();

        assertEquals(2, resultado.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void getUsuarioById_existente_deberiaRetornar200() {
        Rol rolAdmin = new Rol(1L, "ADMIN");
        Usuario u1 = new Usuario(1L, "Rodrigo", "pass", rolAdmin);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(u1));

        ResponseEntity<Usuario> respuesta = usuarioController.getUsuarioById(1L);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(u1, respuesta.getBody());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void getUsuarioById_inexistente_deberiaRetornar404() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> respuesta = usuarioController.getUsuarioById(99L);

        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
        verify(usuarioRepository).findById(99L);
    }

    @Test
    void register_deberiaEncriptarPasswordYGuardar() {
        Rol rolUser = new Rol(2L, "USER");
        Usuario u1 = new Usuario(null, "Rodrigo", "1234", rolUser);
        Usuario uGuardado = new Usuario(1L, "Rodrigo", "encodedPass", rolUser);

        when(passwordEncoder.encode("1234")).thenReturn("encodedPass");
        when(usuarioRepository.save(u1)).thenReturn(uGuardado);

        ResponseEntity<Usuario> respuesta = usuarioController.register(u1);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("encodedPass", respuesta.getBody().getPassword());
        verify(passwordEncoder).encode("1234");
        verify(usuarioRepository).save(u1);
    }

    @Test
    void updateUsuario_existente_deberiaActualizarDatos() {
        Rol rolUser = new Rol(2L, "USER");
        Rol rolAdmin = new Rol(1L, "ADMIN");

        Usuario existente = new Usuario(1L, "Rodrigo", "oldPass", rolUser);
        Usuario detalles = new Usuario(null, "NuevoNombre", "nuevaPass", rolAdmin);
        Usuario actualizado = new Usuario(1L, "NuevoNombre", "encodedPass", rolAdmin);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(passwordEncoder.encode("nuevaPass")).thenReturn("encodedPass");
        when(usuarioRepository.save(existente)).thenReturn(actualizado);

        ResponseEntity<Usuario> respuesta = usuarioController.updateUsuario(1L, detalles);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("NuevoNombre", respuesta.getBody().getUsername());
        assertEquals("encodedPass", respuesta.getBody().getPassword());
        verify(usuarioRepository).save(existente);
    }

    @Test
    void updateUsuario_inexistente_deberiaRetornar404() {
        Rol rolAdmin = new Rol(1L, "ADMIN");
        Usuario detalles = new Usuario(null, "NuevoNombre", "nuevaPass", rolAdmin);

        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> respuesta = usuarioController.updateUsuario(99L, detalles);

        assertEquals(404, respuesta.getStatusCodeValue());
        verify(usuarioRepository).findById(99L);
    }

    @Test
    void deleteUsuario_existente_deberiaEliminarYRetornar204() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> respuesta = usuarioController.deleteUsuario(1L);

        assertEquals(204, respuesta.getStatusCodeValue());
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void deleteUsuario_inexistente_deberiaRetornar404() {
        when(usuarioRepository.existsById(99L)).thenReturn(false);

        ResponseEntity<Void> respuesta = usuarioController.deleteUsuario(99L);

        assertEquals(404, respuesta.getStatusCodeValue());
        verify(usuarioRepository).existsById(99L);
    }
}
