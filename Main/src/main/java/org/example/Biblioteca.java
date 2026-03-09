package org.example;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    public List<Usuarios> listaUsuarios = new ArrayList<>();

    //Criando um usuário
    public void cadastrarUsuario(String nome) {
        Usuarios novoUsuario = new Usuarios(nome);
        listaUsuarios.add(novoUsuario);
        System.out.println("Usuário " + nome + " cadastrado com sucesso!");
    }

    //Mostrar o usuário cadastrado
    public void exibirUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("A biblioteca está vazia.");
        } else {
            System.out.println("\n--- Lista de Usuários ---");
            for (int i = 0; i < listaUsuarios.size(); i++) {
                System.out.println(i + " - " + listaUsuarios.get(i).getNome());
            }
        }
    }

    //Atualizando o usuário cadastrado
    public void atualizarUsuario(int indice, String novoNome) {
        if (indice >= 0 && indice < listaUsuarios.size()) {
            listaUsuarios.get(indice).setNome(novoNome);
            System.out.println("Usuário atualizado!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    //Apagando o usuário cadastrado
    public void removerUsuario(int indice) {
        if (indice >= 0 && indice < listaUsuarios.size()) {
            Usuarios removido = listaUsuarios.remove(indice);
            System.out.println("Usuário " + removido.getNome() + " removido.");
        } else {
            System.out.println("Índice inválido.");
        }
    }
}