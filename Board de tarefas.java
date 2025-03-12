import java.sql.*;
import java.util.Scanner;

public class BoardManager {

    // Constantes para a conexão com o banco de dados MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db"; // URL do banco de dados
    private static final String DB_USER = "Marcos"; // Nome de usuário do banco de dados
    private static final String DB_PASSWORD = "DetonaTECH"; // Senha do banco de dados

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para ler a entrada do usuário
        int opcao; // Variável para armazenar a opção escolhida pelo usuário

        do {
            exibirMenu(); // Chama o método para exibir o menu
            opcao = scanner.nextInt(); // Lê a opção escolhida pelo usuário
            scanner.nextLine(); // Limpa o buffer do Scanner

            switch (opcao) {
                case 1:
                    criarNovoBoard(scanner); // Chama o método para criar um novo board
                    break;
                case 2:
                    selecionarBoard(scanner); // Chama o método para selecionar um board existente
                    break;
                case 3:
                    excluirBoard(scanner); // Chama o método para excluir um board
                    break;
                case 0:
                    System.out.println("Saindo..."); // Exibe a mensagem de saída
                    break;
                default:
                    System.out.println("Opção inválida!"); // Exibe mensagem de erro para opção inválida
            }
        } while (opcao != 0); // Repete o loop até o usuário escolher a opção 0

        scanner.close(); // Fecha o Scanner
    }

    // Método para exibir o menu de opções
    private static void exibirMenu() {
        System.out.println("\n--- Gerenciador de Boards ---");
        System.out.println("1. Criar novo board");
        System.out.println("2. Selecionar board");
        System.out.println("3. Excluir board");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Método para criar um novo board
    private static void criarNovoBoard(Scanner scanner) {
        System.out.print("Nome do board: ");
        String nome = scanner.nextLine(); // Lê o nome do board

        System.out.print("Descrição do board: ");
        String descricao = scanner.nextLine(); // Lê a descrição do board

        // Bloco try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Cria a conexão com o banco de dados
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO boards (nome, descricao) VALUES (?, ?)")) { // Prepara a consulta SQL
            stmt.setString(1, nome); // Define o valor do primeiro parâmetro (nome)
            stmt.setString(2, descricao); // Define o valor do segundo parâmetro (descrição)
            stmt.executeUpdate(); // Executa a consulta SQL
            System.out.println("Board criado com sucesso!"); // Exibe mensagem de sucesso
        } catch (SQLException e) { // Captura exceções SQL
            System.err.println("Erro ao criar board: " + e.getMessage()); // Exibe mensagem de erro
        }
    }

    // Método para selecionar um board existente (ainda não implementado)
    private static void selecionarBoard(Scanner scanner) {
        // TODO: Implementar a lógica para selecionar um board
    }

    // Método para excluir um board (ainda não implementado)
    private static void excluirBoard(Scanner scanner) {
        // TODO: Implementar a lógica para excluir um board
    }
}