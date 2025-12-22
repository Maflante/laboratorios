package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaboratorioAnalisisTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetAndGetAllFields() {
        LaboratorioAnalisis la = new LaboratorioAnalisis();
        la.setLaboratorioId(10L);
        la.setAnalisisId(20L);

        assertEquals(Long.valueOf(10L), la.getLaboratorioId());
        assertEquals(Long.valueOf(20L), la.getAnalisisId());
    }

    @Test
    void allArgsConstructorAndGetters_shouldReturnProvidedValues() {
        LaboratorioAnalisis la = new LaboratorioAnalisis(1L, 2L);

        assertEquals(Long.valueOf(1L), la.getLaboratorioId());
        assertEquals(Long.valueOf(2L), la.getAnalisisId());
    }

    @Test
    void equalsAndHashCode_sameValues_shouldBeEqual() {
        LaboratorioAnalisis a = new LaboratorioAnalisis(5L, 6L);
        LaboratorioAnalisis b = new LaboratorioAnalisis(5L, 6L);
        LaboratorioAnalisis c = new LaboratorioAnalisis(5L, 7L);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void classLoads() throws Exception {
        Class<?> cls = Class.forName("com.example.demo.entity.LaboratorioAnalisis");
        assertNotNull(cls);
    }

    @Test
    void toString_containsClassAndIds() {
        LaboratorioAnalisis la = new LaboratorioAnalisis(7L, 8L);
        String s = la.toString();
        assertNotNull(s);
        assertTrue(s.contains("LaboratorioAnalisis"));
        assertTrue(s.contains("7"));
        assertTrue(s.contains("8"));
    }
}
