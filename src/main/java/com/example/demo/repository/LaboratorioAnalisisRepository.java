package com.example.demo.repository;

import com.example.demo.entity.LaboratorioAnalisis;
import com.example.demo.entity.LaboratorioAnalisisId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioAnalisisRepository extends JpaRepository<LaboratorioAnalisis, LaboratorioAnalisisId> {}