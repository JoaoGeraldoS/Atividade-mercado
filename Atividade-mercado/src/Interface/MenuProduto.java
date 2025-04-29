package Interface;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import java.util.Locale;
import model.Categoria;

import java.util.Scanner;

public class MenuProduto extends GenericsMenu{
    public void menuProduto(){
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        genericMenuProduto();

        System.out.print("\nDigite a opção: ");
        int opcao = scanner.nextInt();

        ProdutoDAO produtoDAO = new ProdutoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        switch (opcao){
            case 1 -> {
                scanner.nextLine();
                System.out.print("Digite o nome: ");
                String nome = scanner.nextLine();

                System.out.print("Digite o preco: ");
                double preco = scanner.nextDouble();

                System.out.print("Digite a quantidade: ");
                int qtd = scanner.nextInt();

                scanner.nextLine();
                System.out.print("Digite o nome da categoria: ");
                String nomeCategoria = scanner.nextLine();

                Categoria categoria = categoriaDAO.buscarCategoria(nomeCategoria);
                produtoDAO.adicionarProduto(nome, preco, qtd, categoria.getId());
            }
            case 2 -> produtoDAO.verProduto().forEach(System.out::println);

            case 3 -> {
                System.out.print("Digite o id do produto: ");
                produtoDAO.apagarProduto(scanner.nextInt());
            }

            case 4 -> System.out.println("Saindo...");

            default -> System.out.println("Entrada invalida!");

        }
    }
}
