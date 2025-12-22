package com.example.demo.repository;

import com.example.demo.entity.Laboratorio;
import com.example.demo.entity.TiposAnalisis;
import com.example.demo.entity.LaboratorioAnalisis;
import com.example.demo.entity.LaboratorioAnalisisId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class LaboratorioAnalisisRepositoryTest {

    @Autowired
    private LaboratorioAnalisisRepository laboratorioAnalisisRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private TiposAnalisisRepository tiposAnalisisRepository;

    @Test
    void saveYFindById_deberiaPersistirYRecuperarLaboratorioAnalisis() {
        // Crear laboratorio
        Laboratorio lab = new Laboratorio(null, "Lab A", 20, "Activo", null);
        laboratorioRepository.save(lab);

        // Crear tipo de análisis
        TiposAnalisis analisis = new TiposAnalisis(null, "Hemograma", "Examen de sangre completo");
        tiposAnalisisRepository.save(analisis);

        // Crear relación usando IDs
        LaboratorioAnalisisId id = new LaboratorioAnalisisId(lab.getId(), analisis.getId());
        LaboratorioAnalisis laboratorioAnalisis = new LaboratorioAnalisis(lab.getId(), analisis.getId());

        laboratorioAnalisisRepository.save(laboratorioAnalisis);

        Optional<LaboratorioAnalisis> resultado = laboratorioAnalisisRepository.findById(id);

        assertTrue(resultado.isPresent());
        assertEquals(lab.getId(), resultado.get().getLaboratorioId());
        assertEquals(analisis.getId(), resultado.get().getAnalisisId());
    }
}
