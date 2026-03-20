package com.paulobezerra.gerenciador_biblioteca.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LivroRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 1, max = 200, message = "Título deve ter entre 1 e 200 caracteres")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    @Size(min = 3, max = 100, message = "Autor deve ter entre 3 e 100 caracteres")
    private String autor;

    @NotBlank(message = "ISBN é obrigatório")
    @Size(min = 10, max = 13, message = "ISBN deve ter entre 10 e 13 caracteres")
    private String isbn;
}