package com.sistemavidaplus.repository;
import com.sistemavidaplus.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByLogin(String login);
}