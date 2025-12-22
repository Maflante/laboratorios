package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class LaboratorioAnalisisIdTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetAndGetAllFields() {
        LaboratorioAnalisisId id = new LaboratorioAnalisisId();
        id.setLaboratorioId(100L);
        id.setAnalisisId(200L);

        assertEquals(Long.valueOf(100L), id.getLaboratorioId());
        assertEquals(Long.valueOf(200L), id.getAnalisisId());
    }

    @Test
    void allArgsConstructorAndGetters_shouldReturnProvidedValues() {
        LaboratorioAnalisisId id = new LaboratorioAnalisisId(1L, 2L);

        assertEquals(Long.valueOf(1L), id.getLaboratorioId());
        assertEquals(Long.valueOf(2L), id.getAnalisisId());
    }

    @Test
    void equalsAndHashCode_sameValues_shouldBeEqual() {
        LaboratorioAnalisisId a = new LaboratorioAnalisisId(5L, 6L);
        LaboratorioAnalisisId b = new LaboratorioAnalisisId(5L, 6L);
        LaboratorioAnalisisId c = new LaboratorioAnalisisId(5L, 7L);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void serialization_roundTrip_shouldPreserveValues() throws Exception {
        LaboratorioAnalisisId original = new LaboratorioAnalisisId(42L, 84L);

        byte[] bytes;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(original);
            out.flush();
            bytes = bos.toByteArray();
        }

        LaboratorioAnalisisId deserialized;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            deserialized = (LaboratorioAnalisisId) in.readObject();
        }

        assertNotNull(deserialized);
        assertEquals(original, deserialized);
        assertEquals(original.getLaboratorioId(), deserialized.getLaboratorioId());
        assertEquals(original.getAnalisisId(), deserialized.getAnalisisId());
    }

    @Test
    void toString_containsIds() {
        LaboratorioAnalisisId id = new LaboratorioAnalisisId(7L, 8L);
        String s = id.toString();
        assertNotNull(s);
        assertTrue(s.contains("7") || s.contains("laboratorioId") || s.contains("analisisId"));
    }
}
