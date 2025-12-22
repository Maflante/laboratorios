package com.example.demo.repository;

import com.example.demo.entity.Laboratorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class LaboratorioRepositoryTest {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Test
    void saveYFindById_deberiaPersistirYRecuperarLaboratorio() {
        Laboratorio lab = new Laboratorio(null, "Lab A", 20, "Activo", null);
        laboratorioRepository.save(lab);

        Optional<Laboratorio> resultado = laboratorioRepository.findById(lab.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Lab A", resultado.get().getNombre());
    }
}
