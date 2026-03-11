package com.paulo.aplicacao_spring.model;

public class Usuario {
    private String nome;

    // Construtor vazio (Necessário para o Spring)
    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void putNome(String nome){
        this.nome = nome;
    }
}