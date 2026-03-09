package org.example;

public class Usuarios {
    // Atributo (característica do objeto)
    public String nome;

    // Construtor (como o objeto nasce)
    public Usuarios(String nome) {
        this.nome = nome;
    }

    // Getters e Setters (como acessamos e alteramos o nome com segurança)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
