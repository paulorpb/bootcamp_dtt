package com.paulobezerra.gerenciador_biblioteca.dto;

import lombok.Data;

@Data
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Boolean disponivel;
}