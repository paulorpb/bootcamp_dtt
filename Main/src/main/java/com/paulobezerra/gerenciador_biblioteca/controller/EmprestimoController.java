package com.paulobezerra.gerenciador_biblioteca.controller;

import com.paulobezerra.gerenciador_biblioteca.entity.Emprestimo;
import com.paulobezerra.gerenciador_biblioteca.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> registrar(
            @RequestParam Long usuarioId,
            @RequestParam Long livroId) {
        return ResponseEntity.ok(emprestimoService.registrar(usuarioId, livroId));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.devolver(id));
    }
}