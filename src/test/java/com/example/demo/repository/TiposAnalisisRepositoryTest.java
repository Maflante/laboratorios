package com.example.demo.repository;

import com.example.demo.entity.TiposAnalisis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TiposAnalisisRepositoryTest {

    @Autowired
    private TiposAnalisisRepository tiposAnalisisRepository;

    @Test
    void saveYFindById_deberiaPersistirYRecuperarTiposAnalisis() {
        TiposAnalisis analisis = new TiposAnalisis(null, "Hemograma", "Examen de sangre completo");
        tiposAnalisisRepository.save(analisis);

        Optional<TiposAnalisis> resultado = tiposAnalisisRepository.findById(analisis.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Hemograma", resultado.get().getNombre());
    }
}
