package com.paulo.aplicacao_spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity // Diz que isso é uma tabela
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor // Aproveitando o Lombok que você já tem no pom
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // O banco gerenciará este ID agora

    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }
}