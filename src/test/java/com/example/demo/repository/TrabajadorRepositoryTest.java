package com.example.demo.repository;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Trabajador;
import com.example.demo.entity.Laboratorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TrabajadorRepositoryTest {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Test
    void findByUsuario_Rol_Id_deberiaRetornarTrabajadoresPorRol() {
        // Crear rol
        Rol rol = new Rol(null, "TECNICO");
        rolRepository.save(rol);

        // Crear usuario con rol
        Usuario usuario = new Usuario(null, "juanp", "clave123", rol);
        usuarioRepository.save(usuario);

        // Crear laboratorio
        Laboratorio lab = new Laboratorio(null, "Lab Central", 50, "Activo", null);
        laboratorioRepository.save(lab);

        // Crear trabajador con usuario y laboratorio
        Trabajador trabajador = new Trabajador(
                null,
                "Juan",
                "Perez",
                "juanp@example.com",
                "123456789",
                "ACTIVO",
                lab,
                usuario
        );
        trabajadorRepository.save(trabajador);

        // Ejecutar query personalizada
        List<Trabajador> resultado = trabajadorRepository.findByUsuario_Rol_Id(rol.getId());

        assertFalse(resultado.isEmpty());
        assertEquals("Juan", resultado.get(0).getNombre());
        assertEquals("Perez", resultado.get(0).getApellido());
        assertEquals("TECNICO", resultado.get(0).getUsuario().getRol().getNombre());
    }
}
