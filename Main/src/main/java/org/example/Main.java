package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nomeUsuario = null;

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Exibir usuário");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Apagar usuário");
            System.out.print("Digite a opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    //incluir o nome de um usuário
                    System.out.println("Digite o nome do usuário: ");
                    nomeUsuario = sc.nextLine();
                    break;
                case 2:
                    //Exibindo o nome do usuário
                    System.out.println("Usuário cadastrado: " + nomeUsuario);
                    break;
                case 3:
                    //Atualizando o nome do usuário
                    System.out.print("Digite um novo nome: ");
                    nomeUsuario = sc.nextLine();
                    break;
                case 4:
                    //Apagando o nome do usuário
                    nomeUsuario = null;
                    System.out.println("Usuário apagado");
                    break;
                default:
                    System.out.println("Opção inválida; Encerrando...");
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }
}