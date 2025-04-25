package Interface;

import dao.CategoriaDAO;
import model.Categoria;

import java.util.Scanner;

public class  MenuCategoria {

    public void menuCategria() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMenu Categoria\n");
        System.out.println("1- Adicionar categoria\n2 - Ver categorias\n3 - Deletar categoria");
        System.out.print("\nDigite a opção: ");
        int opcao = scanner.nextInt();


        CategoriaDAO categoriaDAO = new CategoriaDAO();

        switch (opcao) {
            case 1 -> {
                scanner.nextLine();
                System.out.print("Digite o nome: ");

                String nome = scanner.nextLine();

                categoriaDAO.adicionarCategoria(nome);
            }
            case 2 -> categoriaDAO.verCategoria().forEach(System.out::println);

            case 3 -> {
                System.out.print("Digite o id da categoria: ");

                int id = scanner.nextInt();
                categoriaDAO.apagarCategoria(id);
            }
            default -> System.out.println("Entrada invalida!");
        }

    }
}
