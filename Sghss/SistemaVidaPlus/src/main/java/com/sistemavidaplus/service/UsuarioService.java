package com.sistemavidaplus.service;
import com.sistemavidaplus.entity.UsuarioEntity;
import com.sistemavidaplus.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UsuarioEntity salvarOuAtualizar(UsuarioEntity usuarioEntity) {
        if (usuarioEntity.getSenha() != null) {
            usuarioEntity.setSenha(passwordEncoder.encode(usuarioEntity.getSenha()));
        }
        return usuarioRepository.save(usuarioEntity);
    }


    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }


    public Optional<UsuarioEntity> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }


    public boolean deletarPorId(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<UsuarioEntity> buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
}