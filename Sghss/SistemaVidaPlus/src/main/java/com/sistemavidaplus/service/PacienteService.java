package com.sistemavidaplus.service;
import com.sistemavidaplus.entity.PacienteEntity;
import com.sistemavidaplus.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;


    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    public PacienteEntity salvarOuAtualizar(PacienteEntity pacienteEntity) {
        return pacienteRepository.save(pacienteEntity);
    }


    public List<PacienteEntity> listarTodos() {
        return pacienteRepository.findAll();
    }


    public Optional<PacienteEntity> buscarPorId(int id) {
        return pacienteRepository.findById(id);
    }


    public boolean deletarPorId(int id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}