package com.paulobezerra.gerenciador_biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("usuario-emprestimos")
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @JsonBackReference("livro-emprestimos")
    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @Column
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private Boolean ativo = true;
}