package com.sistemavidaplus.controller;
import com.sistemavidaplus.entity.ConsultaEntity;
import com.sistemavidaplus.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }


    @GetMapping
    public ResponseEntity<List<ConsultaEntity>> listarTodas() {
        return ResponseEntity.ok(consultaService.listarTodas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConsultaEntity> buscarPorId(@PathVariable int id) {
        return consultaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ConsultaEntity> criarConsulta(@Valid @RequestBody ConsultaEntity consultaEntity) {
        return ResponseEntity.ok(consultaService.salvarOuAtualiza(consultaEntity));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ConsultaEntity> atualizarConsulta(@PathVariable int id, @Valid @RequestBody ConsultaEntity consultaEntity) {
        if (consultaService.buscarPorId(id).isEmpty())
            return ResponseEntity.notFound().build();
        consultaEntity.setIdConsulta(id);
        return ResponseEntity.ok(consultaService.salvarOuAtualiza(consultaEntity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable int id) {
        return consultaService.cancelarConsulta(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}