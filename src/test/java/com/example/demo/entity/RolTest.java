package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RolTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetAndGetAllFields() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNombre("ADMIN");

        assertEquals(Long.valueOf(1L), rol.getId());
        assertEquals("ADMIN", rol.getNombre());
    }

    @Test
    void allArgsConstructorAndGetters_shouldReturnProvidedValues() {
        Rol rol = new Rol(2L, "USER");

        assertEquals(Long.valueOf(2L), rol.getId());
        assertEquals("USER", rol.getNombre());
    }

    @Test
    void equalsAndHashCode_sameValues_shouldBeEqual() {
        Rol a = new Rol(3L, "MANAGER");
        Rol b = new Rol(3L, "MANAGER");
        Rol c = new Rol(4L, "OTHER");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void classLoads() throws Exception {
        Class<?> cls = Class.forName("com.example.demo.entity.Rol");
        assertNotNull(cls);
    }

    @Test
    void toString_containsClassAndName() {
        Rol rol = new Rol(5L, "TEST_ROLE");
        String s = rol.toString();
        assertNotNull(s);
        assertTrue(s.contains("Rol"));
        assertTrue(s.contains("TEST_ROLE"));
    }
}
