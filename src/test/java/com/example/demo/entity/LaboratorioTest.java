package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LaboratorioTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetAndGetAllFields() {
        Laboratorio lab = new Laboratorio(); // lombok no-args
        lab.setId(1L);
        lab.setNombre("Laboratorio Central");
        lab.setCapacidad(50);
        lab.setEstado("ACTIVO");
        lab.setFechaRegistro(LocalDate.of(2023, 6, 15));

        assertEquals(1L, lab.getId());
        assertEquals("Laboratorio Central", lab.getNombre());
        assertEquals(Integer.valueOf(50), lab.getCapacidad());
        assertEquals("ACTIVO", lab.getEstado());
        assertEquals(LocalDate.of(2023, 6, 15), lab.getFechaRegistro());
    }

    @Test
    void allArgsConstructorAndGetters_shouldReturnProvidedValues() {
        LocalDate fecha = LocalDate.of(2024, 1, 10);
        Laboratorio lab = new Laboratorio(2L, "Lab B", 20, "INACTIVO", fecha);

        assertEquals(2L, lab.getId());
        assertEquals("Lab B", lab.getNombre());
        assertEquals(Integer.valueOf(20), lab.getCapacidad());
        assertEquals("INACTIVO", lab.getEstado());
        assertEquals(fecha, lab.getFechaRegistro());
    }

    @Test
    void equalsAndHashCode_sameIdAndValues_shouldBeEqual() {
        LocalDate fecha = LocalDate.now();
        Laboratorio a = new Laboratorio(3L, "X", 10, "ACTIVO", fecha);
        Laboratorio b = new Laboratorio(3L, "X", 10, "ACTIVO", fecha);
        Laboratorio c = new Laboratorio(4L, "Y", 5, "ACTIVO", fecha);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void toString_shouldContainClassNameAndSomeFields() {
        Laboratorio lab = new Laboratorio(5L, "ToStringLab", 1, "ACTIVO", LocalDate.of(2022, 12, 1));
        String s = lab.toString();
        assertNotNull(s);
        assertTrue(s.contains("Laboratorio"));
        assertTrue(s.contains("ToStringLab"));
    }
}
