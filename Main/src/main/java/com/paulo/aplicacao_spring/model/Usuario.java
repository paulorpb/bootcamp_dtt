package com.paulo.aplicacao_spring.model;

import jakarta.persistence.*; // Importante para o banco
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity // Diz que esta classe é uma tabela no banco
@Getter @Setter @NoArgsConstructor // Se estiver usando Lombok
public class Usuario {

    @Id // Define que o ID é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco gera o ID sozinho (1, 2, 3...)
    private Long id;

    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}