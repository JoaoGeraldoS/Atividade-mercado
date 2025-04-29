package Interface;

import dao.ClienteDAO;
import model.Cliente;
import java.util.Scanner;

public class MenuCliente extends GenericsMenu{
    private final Cliente cliente = new Cliente();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void menuCliente(){
        Scanner scanner = new Scanner(System.in);

        genericMenuCliente();

        System.out.print("Digite a opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();


        switch (opcao){
            case 1 -> {
                System.out.print("Digite o nome: ");

                String nome = scanner.nextLine();

                System.out.print("Digite o cpf: ");
                String cpf = scanner.nextLine();

                cliente.setNome(nome);
                cliente.setCpf(cpf);

                clienteDAO.criarCliente(cliente);
            }
            case 2 -> clienteDAO.verClientes().forEach(System.out::println);

            case 3 -> {
                System.out.print("Digite o id do cliente: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                clienteDAO.apagarCliente(id);
            }

            case 4 -> System.out.println("Saindo...");

            default -> System.out.println("Erro, entrada invalida!");

        }

    }

}
