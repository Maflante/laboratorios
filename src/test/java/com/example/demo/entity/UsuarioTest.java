package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetAndGetAllFields() {
        Usuario u = new Usuario();
        u.setId(1L);
        u.setUsername("rodrigo");
        u.setPassword("secret");
        Rol rol = new Rol(2L, "USER");
        u.setRol(rol);

        assertEquals(Long.valueOf(1L), u.getId());
        assertEquals("rodrigo", u.getUsername());
        assertEquals("secret", u.getPassword());
        assertSame(rol, u.getRol());
    }

    @Test
    void allArgsConstructorAndGetters_shouldReturnProvidedValues() {
        Rol rol = new Rol(3L, "ADMIN");
        Usuario u = new Usuario(4L, "admin", "adminpass", rol);

        assertEquals(Long.valueOf(4L), u.getId());
        assertEquals("admin", u.getUsername());
        assertEquals("adminpass", u.getPassword());
        assertSame(rol, u.getRol());
    }

    @Test
    void equalsAndHashCode_sameValues_shouldBeEqual() {
        Rol rol = new Rol(5L, "MANAGER");
        Usuario a = new Usuario(6L, "userA", "p1", rol);
        Usuario b = new Usuario(6L, "userA", "p1", rol);
        Usuario c = new Usuario(7L, "userC", "p2", rol);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void classLoads() throws Exception {
        Class<?> cls = Class.forName("com.example.demo.entity.Usuario");
        assertNotNull(cls);
    }

    @Test
    void toString_containsUsernameAndRole() {
        Rol rol = new Rol(8L, "TEST_ROLE");
        Usuario u = new Usuario(9L, "toStringUser", "pw", rol);
        String s = u.toString();

        assertNotNull(s);
        assertTrue(s.contains("toStringUser"));
        assertTrue(s.contains("TEST_ROLE"));
    }
}
