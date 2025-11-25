package com.example.demo.repository;

import com.example.demo.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    // Buscar trabajadores por laboratorio
    List<Trabajador> findByLaboratorioId(Long laboratorioId);

    // Buscar trabajadores por rol del usuario asociado
    List<Trabajador> findByUsuario_Rol_Id(Long rolId);
}
