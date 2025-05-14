package com.sistemavidaplus.controller;
import com.sistemavidaplus.entity.UsuarioEntity;
import com.sistemavidaplus.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    //todos os metodos aqui implementados, é acessado somente por usuários do tipo "ADMIN",
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscarPorId(@PathVariable int id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<UsuarioEntity> criarUsuario(@Valid @RequestBody UsuarioEntity usuarioEntity) {
        return ResponseEntity.ok(usuarioService.salvarOuAtualizar(usuarioEntity));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioEntity usuarioEntity) {
        if (usuarioService.buscarPorId(id).isEmpty())
            return ResponseEntity.notFound().build();
        usuarioEntity.setIdUsuario(id);
        return ResponseEntity.ok(usuarioService.salvarOuAtualizar(usuarioEntity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
        return usuarioService.deletarPorId(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}