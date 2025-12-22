package com.example.demo.controller;

import com.example.demo.entity.LaboratorioAnalisis;
import com.example.demo.repository.LaboratorioAnalisisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaboratorioAnalisisControllerTest {

    @Mock
    private LaboratorioAnalisisRepository relRepository;

    @InjectMocks
    private LaboratorioAnalisisController laboratorioAnalisisController;

    @Test
    void asignarAnalisis_deberiaGuardarRelacionYRetornar200() {
        Long labId = 1L;
        Long analisisId = 10L;
        LaboratorioAnalisis rel = new LaboratorioAnalisis(labId, analisisId);

        when(relRepository.save(any(LaboratorioAnalisis.class))).thenReturn(rel);

        ResponseEntity<?> respuesta = laboratorioAnalisisController.asignarAnalisis(labId, analisisId);

        assertEquals(200, respuesta.getStatusCodeValue());
        verify(relRepository).save(any(LaboratorioAnalisis.class));
    }
}
