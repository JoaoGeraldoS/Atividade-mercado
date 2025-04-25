package Interface;

import java.util.Scanner;

public class MenuPrincipal {

    private Scanner scanner = new Scanner(System.in);

    public void menuPrincipal() {

        while (true) {
            System.out.println("\n==== MENU DE PRINCIPAL ====\n");
            System.out.println("1 - Clientes");
            System.out.println("2 - Categorias");
            System.out.println("3 - Produtos");
            System.out.println("4 - Comprar");
            System.out.println("5 - Sair");
            System.out.print("Digite a opção: ");
            int opcao = scanner.nextInt();


            switch (opcao) {
                case 1 -> {
                    MenuCliente menuCliente = new MenuCliente();
                    menuCliente.menuCliente();
                }
                case 2 -> {
                    MenuCategoria menuCategoria = new MenuCategoria();
                    menuCategoria.menuCategria();
                }
                case 3 -> {
                    MenuProduto menuProduto = new MenuProduto();
                    menuProduto.menuProduto();
                }
                case 4 -> {
                    MenuCompra menuCompra = new MenuCompra();
                    menuCompra.menuCompra();
                }

                case 5 -> {
                    return;
                }

                default -> throw new IllegalStateException("Valor invalido " + opcao);
            }
        }
    }
}
