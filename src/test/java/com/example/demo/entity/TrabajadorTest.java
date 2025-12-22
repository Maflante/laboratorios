package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrabajadorTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetAndGetAllFields() {
        Trabajador t = new Trabajador();
        t.setId(1L);
        t.setNombre("Juan");
        t.setApellido("Pérez");
        t.setCorreo("juan.perez@example.com");
        t.setTelefono("+56912345678");
        t.setEstado("ACTIVO");

        Laboratorio lab = new Laboratorio(10L, "Lab Central", 30, "ACTIVO", null);
        t.setLaboratorio(lab);

        Usuario user = new Usuario(); // asumo que Usuario tiene no-args (Lombok)
        t.setUsuario(user);

        assertEquals(Long.valueOf(1L), t.getId());
        assertEquals("Juan", t.getNombre());
        assertEquals("Pérez", t.getApellido());
        assertEquals("juan.perez@example.com", t.getCorreo());
        assertEquals("+56912345678", t.getTelefono());
        assertEquals("ACTIVO", t.getEstado());
        assertSame(lab, t.getLaboratorio());
        assertSame(user, t.getUsuario());
    }

    @Test
    void allArgsConstructorAndGetters_shouldReturnProvidedValues() {
        Laboratorio lab = new Laboratorio(20L, "Lab Secundario", 15, "INACTIVO", null);
        Usuario user = new Usuario();

        Trabajador t = new Trabajador(
                2L,
                "María",
                "González",
                "maria.g@example.com",
                "+56987654321",
                "INACTIVO",
                lab,
                user
        );

        assertEquals(Long.valueOf(2L), t.getId());
        assertEquals("María", t.getNombre());
        assertEquals("González", t.getApellido());
        assertEquals("maria.g@example.com", t.getCorreo());
        assertEquals("+56987654321", t.getTelefono());
        assertEquals("INACTIVO", t.getEstado());
        assertSame(lab, t.getLaboratorio());
        assertSame(user, t.getUsuario());
    }

    @Test
    void equalsAndHashCode_sameValues_shouldBeEqual() {
        Laboratorio lab = new Laboratorio(30L, "Lab A", 10, "ACTIVO", null);
        Usuario user = new Usuario();

        Trabajador a = new Trabajador(3L, "Ana", "Lopez", "a.lopez@example.com", "1111", "ACTIVO", lab, user);
        Trabajador b = new Trabajador(3L, "Ana", "Lopez", "a.lopez@example.com", "1111", "ACTIVO", lab, user);
        Trabajador c = new Trabajador(4L, "Pedro", "Soto", "p.soto@example.com", "2222", "ACTIVO", lab, user);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void classLoads() throws Exception {
        Class<?> cls = Class.forName("com.example.demo.entity.Trabajador");
        assertNotNull(cls);
    }

    @Test
    void toString_containsClassAndKeyFields() {
        Laboratorio lab = new Laboratorio(40L, "Lab ToString", 5, "ACTIVO", null);
        Usuario user = new Usuario();
        Trabajador t = new Trabajador(5L, "To", "String", "ts@example.com", "0000", "ACTIVO", lab, user);

        String s = t.toString();
        assertNotNull(s);
        assertTrue(s.contains("Trabajador"));
        assertTrue(s.contains("To"));
        assertTrue(s.contains("ts@example.com"));
    }
}
