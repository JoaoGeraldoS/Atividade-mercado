package Interface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuPrincipal extends GenericsMenu{

    private final Map<Integer, Runnable> opcoes = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public MenuPrincipal() {
        opcoes.put(1, this::cliente);
        opcoes.put(2, this::categoria);
        opcoes.put(3, this::produtos);
        opcoes.put(4, this::comprar);
    }

    public void menuPrincipal() {
        int opcao;
        do {
            genericMenuPrincipal();

            System.out.print("Digite a opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 5) {
                System.out.println("Saindo...");
                return;
            }

            Runnable runnable = opcoes.get(opcao);
            if (runnable != null) {
                runnable.run();
            } else {
                System.out.println("Entrada invalida!");
            }

        } while (opcao != 0);
    }

    private void cliente() {
        MenuCliente menuCliente = new MenuCliente();
        menuCliente.menuCliente();
    }

    private void categoria() {
        MenuCategoria menuCategoria = new MenuCategoria();
        menuCategoria.menuCategria();
    }

    private void produtos() {
        MenuProduto menuProduto = new MenuProduto();
        menuProduto.menuProduto();
    }

    private void comprar() {
        MenuCompra menuCompra = new MenuCompra();
        menuCompra.menuCompra();
    }
}