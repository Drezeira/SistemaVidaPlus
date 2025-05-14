package com.sistemavidaplus.repository;
import com.sistemavidaplus.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Integer> {

}