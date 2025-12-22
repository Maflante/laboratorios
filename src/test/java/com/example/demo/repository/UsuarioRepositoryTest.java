package com.example.demo.repository;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Test
    void findByUsername_deberiaRetornarUsuarioCorrecto() {
        Rol rol = new Rol(null, "ADMIN");
        rolRepository.save(rol);

        Usuario usuario = new Usuario(null, "rodrigo", "password123", rol);
        usuarioRepository.save(usuario);

        Optional<Usuario> resultado = usuarioRepository.findByUsername("rodrigo");

        assertTrue(resultado.isPresent());
        assertEquals("rodrigo", resultado.get().getUsername());
    }

    @Test
    void findByUsername_deberiaRetornarVacioSiNoExiste() {
        Optional<Usuario> resultado = usuarioRepository.findByUsername("inexistente");
        assertFalse(resultado.isPresent());
    }
}
