package com.paulobezerra.gerenciador_biblioteca.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmprestimoResponseDTO {

    private Long id;
    private UsuarioResponseDTO usuario;
    private LivroResponseDTO livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Boolean ativo;
}