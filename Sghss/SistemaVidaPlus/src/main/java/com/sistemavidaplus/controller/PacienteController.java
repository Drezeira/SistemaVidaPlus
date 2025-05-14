package com.sistemavidaplus.controller;
import com.sistemavidaplus.entity.PacienteEntity;
import com.sistemavidaplus.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @GetMapping
    public ResponseEntity<List<PacienteEntity>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PacienteEntity> buscarPorId(@PathVariable int id) {
        return pacienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<PacienteEntity> criarPaciente(@Valid @RequestBody PacienteEntity pacienteEntity) {
        return ResponseEntity.ok(pacienteService.salvarOuAtualizar(pacienteEntity));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PacienteEntity> atualizarPaciente(@PathVariable int id, @Valid @RequestBody PacienteEntity pacienteEntity) {
        if (pacienteService.buscarPorId(id).isEmpty())
            return ResponseEntity.notFound().build();
        pacienteEntity.setIdPaciente(id);
        return ResponseEntity.ok(pacienteService.salvarOuAtualizar(pacienteEntity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable int id) {
        return pacienteService.deletarPorId(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}