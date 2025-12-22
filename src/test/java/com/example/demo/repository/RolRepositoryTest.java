package com.example.demo.repository;

import com.example.demo.entity.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class RolRepositoryTest {

    @Autowired
    private RolRepository rolRepository;

    @Test
    void saveYFindById_deberiaPersistirYRecuperarRol() {
        Rol rol = new Rol(null, "USER");
        rolRepository.save(rol);

        Optional<Rol> resultado = rolRepository.findById(rol.getId());

        assertTrue(resultado.isPresent());
        assertEquals("USER", resultado.get().getNombre());
    }
}
