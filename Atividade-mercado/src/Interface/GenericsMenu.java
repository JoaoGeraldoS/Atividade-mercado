package Interface;

public  class GenericsMenu {

    public static void genericMenuCompra() {
        System.out.println("\n==== MENU DE COMPRAS ====");
        System.out.println("1 - Registrar nova compra");
        System.out.println("2 - Listar todas as compras");
        System.out.println("3 - Ver produtos de uma compra específica");
        System.out.println("4 - Iniciar compra e adicionar produtos");
        System.out.println("5 - Ver todas as compras com seus produtos");
        System.out.println("6 - Ver todas as compras que contêm um determinado produto");
        System.out.println("7 - Excluir um produto de uma compra");
        System.out.println("8 - Voltar ao menu principal");
    }

    public static void genericMenuPrincipal() {
        System.out.println("\n==== MENU DE PRINCIPAL ====\n");
        System.out.println("1 - Clientes");
        System.out.println("2 - Categorias");
        System.out.println("3 - Produtos");
        System.out.println("4 - Comprar");
        System.out.println("5 - Sair");
    }

    public static void genericMenuCliente() {
        System.out.println("\n==== MENU DE CLIENTE ====\n");
        System.out.println("1- Adicionar cliente");
        System.out.println("2 - Ver clientes");
        System.out.println("3 - Deletar cliente");
        System.out.println("4 - Sair");
    }

    public static void genericMenuCategoria() {
        System.out.println("\nMenu Categoria\n");
        System.out.println("1- Adicionar categoria");
        System.out.println("2 - Ver categorias");
        System.out.println("3 - Deletar categoria");
        System.out.println("4 - Sair");
    }

    public static void genericMenuProduto() {
        System.out.println("\n==== MENU DE PRODUTO ====\n");
        System.out.println("1- Adicionar produto");
        System.out.println("2 - Ver produtos");
        System.out.println("3 - Deletar produto");
        System.out.println("4 - Sair");
    }
}
