package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca minhaBiblioteca = new Biblioteca();

        int opcao;
        do {
            System.out.println("\n * Menu de cadastro Biblioteca * ");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Exibir usuário");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Apagar usuário");
            System.out.println("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    //incluir o nome de um usuário
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    minhaBiblioteca.cadastrarUsuario(nome);
                    break;
                case 2:
                    //Exibindo o nome do usuário
                    minhaBiblioteca.exibirUsuarios();
                   break;
                case 3:
                    //Atualizando o nome do usuário
                    minhaBiblioteca.exibirUsuarios(); // Mostra a lista para o usuário saber qual nome escolher
                    System.out.print("Digite o número do índice que deseja atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o novo nome: ");
                    String novoNome = sc.nextLine();
                    minhaBiblioteca.atualizarUsuario(idAtualizar, novoNome);
                    break;
                case 4:
                    //Apagando o nome do usuário
                    minhaBiblioteca.exibirUsuarios();
                    System.out.print("Digite o número do índice que deseja apagar: ");
                    int idApagar = sc.nextInt();
                    minhaBiblioteca.removerUsuario(idApagar);
                    break;
                default:
                    System.out.println("Opção inválida; Encerrando...");
                    break;
            }
        } while (opcao > 0 && opcao < 5 );

        sc.close();
    }
}