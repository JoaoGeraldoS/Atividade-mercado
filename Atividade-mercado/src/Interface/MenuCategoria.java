package Interface;

import dao.CategoriaDAO;
import model.Categoria;

import java.util.Scanner;

public class  MenuCategoria extends GenericsMenu{

    public void menuCategria() {
        Scanner scanner = new Scanner(System.in);

        genericMenuCategoria();

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

            case 4 -> System.out.println("Saindo...");

            default -> System.out.println("Entrada invalida!");
        }
    }
}
