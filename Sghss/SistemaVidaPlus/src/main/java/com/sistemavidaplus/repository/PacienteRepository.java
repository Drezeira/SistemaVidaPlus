package com.sistemavidaplus.repository;
import com.sistemavidaplus.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {
}