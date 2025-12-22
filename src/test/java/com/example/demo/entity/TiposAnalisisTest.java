package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TiposAnalisisTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetAndGetAllFields() {
        TiposAnalisis t = new TiposAnalisis();
        t.setId(1L);
        t.setNombre("Hemograma");
        t.setDescripcion("Analisis de sangre completo");

        assertEquals(Long.valueOf(1L), t.getId());
        assertEquals("Hemograma", t.getNombre());
        assertEquals("Analisis de sangre completo", t.getDescripcion());
    }

    @Test
    void allArgsConstructorAndGetters_shouldReturnProvidedValues() {
        TiposAnalisis t = new TiposAnalisis(2L, "PCR", "Detección de ARN viral");

        assertEquals(Long.valueOf(2L), t.getId());
        assertEquals("PCR", t.getNombre());
        assertEquals("Detección de ARN viral", t.getDescripcion());
    }

    @Test
    void equalsAndHashCode_sameValues_shouldBeEqual() {
        TiposAnalisis a = new TiposAnalisis(3L, "Bioquímica", "Panel bioquímico");
        TiposAnalisis b = new TiposAnalisis(3L, "Bioquímica", "Panel bioquímico");
        TiposAnalisis c = new TiposAnalisis(4L, "Inmunología", "Pruebas inmunológicas");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void classLoads() throws Exception {
        Class<?> cls = Class.forName("com.example.demo.entity.TiposAnalisis");
        assertNotNull(cls);
    }

    @Test
    void toString_containsClassAndFields() {
        TiposAnalisis t = new TiposAnalisis(5L, "Hormonas", "Perfil hormonal");
        String s = t.toString();
        assertNotNull(s);
        assertTrue(s.contains("TiposAnalisis"));
        assertTrue(s.contains("Hormonas"));
        assertTrue(s.contains("Perfil hormonal"));
    }
}
