package com.paulobezerra.gerenciador_biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Título é obrigatório")
    @Size(min = 1, max = 200, message = "Título deve ter entre 1 e 200 caracteres")
    private String titulo;

    @Column(nullable = false)
    @NotBlank(message = "Autor é obrigatório")
    @Size(min = 3, max = 100, message = "Autor deve ter entre 3 e 100 caracteres")
    private String autor;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "ISBN é obrigatório")
    @Size(min = 10, max = 13, message = "ISBN deve ter entre 10 e 13 caracteres")
    private String isbn;

    @Column(nullable = false)
    private Boolean disponivel = true;

    @JsonManagedReference("livro-emprestimos")
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;
}