package com.paulobezerra.gerenciador_biblioteca.controller;

import com.paulobezerra.gerenciador_biblioteca.dto.EmprestimoResponseDTO;
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
    public ResponseEntity<EmprestimoResponseDTO> registrar(
            @RequestParam Long usuarioId,
            @RequestParam Long livroId) {
        return ResponseEntity.ok(emprestimoService.registrar(usuarioId, livroId));
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<EmprestimoResponseDTO> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.devolver(id));
    }
}