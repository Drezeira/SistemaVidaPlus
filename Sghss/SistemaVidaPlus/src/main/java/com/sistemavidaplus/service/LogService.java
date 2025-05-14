package com.sistemavidaplus.service;
import com.sistemavidaplus.entity.LogEntity;
import com.sistemavidaplus.repository.LogRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    private final LogRepository logRepository;


    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    public LogEntity salvarOuAtualizar(LogEntity logEntity) {
        return logRepository.save(logEntity);
    }


    public List<LogEntity> listarTodos() {
        return logRepository.findAll();
    }


    public Optional<LogEntity> buscarPorId(int id) {
        return logRepository.findById(id);
    }

    // metodo referente a deletar um log, atraves de um indentificador. porém é um metodo mais para testes.
    public boolean deletarPorId(int id) {
        if (logRepository.existsById(id)) {
            logRepository.deleteById(id);
            return true;
        }
        return false;
    }
}