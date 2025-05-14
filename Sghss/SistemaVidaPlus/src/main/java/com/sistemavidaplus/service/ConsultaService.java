package com.sistemavidaplus.service;
import com.sistemavidaplus.entity.ConsultaEntity;
import com.sistemavidaplus.entity.PacienteEntity;
import com.sistemavidaplus.entity.UsuarioEntity;
import com.sistemavidaplus.repository.ConsultaRepository;
import com.sistemavidaplus.repository.PacienteRepository;
import com.sistemavidaplus.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;


    public ConsultaService(ConsultaRepository consultaRepository,
                           PacienteRepository pacienteRepository,
                           UsuarioRepository usuarioRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // metodo que serve pra agendar ou reagendar uma consulta
    public ConsultaEntity salvarOuAtualiza(ConsultaEntity consultaEntity) {
        Optional<PacienteEntity> pacienteOpt = pacienteRepository.findById(consultaEntity.getPaciente().getIdPaciente());
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(consultaEntity.getUsuario().getIdUsuario());
        if (pacienteOpt.isEmpty() || usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Paciente ou Usuário inválido");
        }
        return consultaRepository.save(consultaEntity);
    }


    public List<ConsultaEntity> listarTodas() {
        return consultaRepository.findAll();
    }


    public Optional<ConsultaEntity> buscarPorId(int id) {
        return consultaRepository.findById(id);
    }


    public boolean cancelarConsulta(int id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
            return true;

        }
        return false;
    }
}