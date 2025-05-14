package com.sistemavidaplus.controller;
import com.sistemavidaplus.entity.LogEntity;
import com.sistemavidaplus.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/log")
public class LogController {

    private final LogService logService;
    //todos os metodos aqui implementados, é acessado somente por usuários do tipo "ADMIN",
    public LogController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping
    public ResponseEntity<List<LogEntity>> listarTodos() {
        return ResponseEntity.ok(logService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<LogEntity> buscarPorId(@PathVariable int id) {
        Optional<LogEntity> logOpt = logService.buscarPorId(id);
        return logOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Permite apagar um log, e está acessível somente pelo tipo ADMIN, porém, é um conteúdo mais para testes
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLog(@PathVariable int id) {
        boolean removido = logService.deletarPorId(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}