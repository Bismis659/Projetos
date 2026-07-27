package org.example;

import java.sql.*;
import java.util.Scanner;

public class SistemaAlunos {
    static Connection conn;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/escola";
        String usuario = "root";
        String senha = "Bismisplay659#";
        try {
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Menu.exibir();
            System.out.println();
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Qual o nome do aluno(a)?");
                    String nome = scanner.nextLine();
                    System.out.println("Qual a idade do aluno(a)?");
                    int idade = scanner.nextInt();
                    System.out.println();
                    Menu.adicionarAluno(conn, nome, idade);
                    break;
                case 2:
                    Menu.listarAlunos(conn);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("ID do aluno: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova idade: ");
                    int novaIdade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();
                    Menu.atualizarAluno(conn, id, novoNome, novaIdade);
                    break;
                case 4:
                    System.out.println("Qual aluno(a) remover (ID)?");
                    int idRemover = scanner.nextInt();
                    System.out.println();
                    Menu.removerAluno(conn, idRemover);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
            }
        }
    }
}

class Menu {
    public static void exibir() {
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Listar alunos");
        System.out.println("3 - Atualizar idade");
        System.out.println("4 - Remover aluno");
        System.out.println("0 - Sair");
    }

    public static void adicionarAluno(Connection conn, String nome, int idade) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO alunos (nome, idade) VALUES (?, ?)");
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.executeUpdate();
            System.out.println("Aluno adicionado!");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void listarAlunos(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM alunos");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Nome: " + rs.getString("nome") +
                        " | Idade: " + rs.getInt("idade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void atualizarAluno(Connection conn, int id, String novoNome, int novaIdade) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE alunos SET nome = ?, idade = ? WHERE id = ?");
            stmt.setString(1, novoNome);
            stmt.setInt(2, novaIdade);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Aluno atualizado!");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void removerAluno(Connection conn, int idRemover) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM alunos WHERE id = ?");
            stmt.setInt(1, idRemover);
            stmt.executeUpdate();
            System.out.println("Aluno removido!");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}