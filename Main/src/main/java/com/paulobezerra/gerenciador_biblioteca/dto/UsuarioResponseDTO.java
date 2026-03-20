package com.paulobezerra.gerenciador_biblioteca.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}