package Interface;

import dao.CompraDAO;
import dao.CompraProdutoDAO;
import dao.ProdutoDAO;
import model.Compra;
import model.CompraProduto;
import model.Produto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MenuCompra {

    private final CompraDAO compraDAO = new CompraDAO();
    private final CompraProdutoDAO compraProdutoDAO = new CompraProdutoDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public void menuCompra() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n==== MENU DE COMPRAS ====");
        System.out.println("1 - Registrar nova compra");
        System.out.println("2 - Listar todas as compras");
        System.out.println("3 - Ver produtos de uma compra específica");
        System.out.println("4 - Iniciar compra e adicionar produtos");
        System.out.println("5 - Ver todas as compras com seus produtos");
        System.out.println("6 - Ver todas as compras que contêm um determinado produto");
        System.out.println("7 - Excluir um produto de uma compra");
        System.out.println("8 - Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");


        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                scanner.nextLine();
                System.out.print("Digite o ID do cliente: ");
                int idCliente = scanner.nextInt();
                Compra novaCompra = compraDAO.adicionarCompra(idCliente);
                System.out.println("Compra registrada com ID: " + novaCompra.getId());
            }
            case 2 -> {
                List<Compra> compras = compraDAO.listarCompras();
                if (compras.isEmpty()) {
                    System.out.println("Nenhuma compra encontrada.");
                } else {
                    for (Compra compra : compras) {
                        System.out.printf("ID: %d | Cliente ID: %d | Data: %s%n",
                                compra.getId(), compra.getClienteId(), compra.getData());
                    }
                }
            }
            case 3 -> {
                System.out.print("Digite o ID da compra: ");
                int idCompra = scanner.nextInt();

                List<CompraProduto> produtos = compraProdutoDAO.listarProdutosPorCompra(idCompra);
                if (produtos.isEmpty()) {
                    System.out.println("Nenhum produto vinculado a essa compra.");
                } else {
                    for (CompraProduto cp : produtos) {
                        Produto produto = produtoDAO.buscarPorId(cp.getProdutoId());
                        String nomeProduto = (produto != null) ? produto.getNome() : "Produto não encontrado";
                        System.out.printf("Nome Produto %s | Quantidade: %d%n", nomeProduto, cp.getQuantidade());
                    }
                }
            }
            case 4 -> {
                System.out.print("Digite o ID do cliente: ");
                int clienteId = scanner.nextInt();

                Compra compra = compraDAO.adicionarCompra(clienteId);
                System.out.println("Compra registrada com ID: " + compra.getId());

                while (true) {
                    System.out.print("ID do produto (0 para finalizar): ");
                    int produtoId = scanner.nextInt();
                    if (produtoId == 0) break;

                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();

                    CompraProduto cp = new CompraProduto();
                    cp.setCompraId(compra.getId());
                    cp.setProdutoId(produtoId);
                    cp.setQuantidade(quantidade);

                    compraProdutoDAO.adicionarProdutoCompra(cp);
                    System.out.println("Produto adicionado à compra.");
                }

                System.out.println("Compra finalizada.");
            }
            case 5 -> {
                List<Compra> compras = compraDAO.listarCompras();

                if (compras.isEmpty()) {
                    System.out.println("Nenhuma compra encontrada.");
                } else {
                    for (Compra compra : compras) {
                        System.out.printf("\nCompra ID: %d | Cliente ID: %d | Data: %s%n",
                                compra.getId(), compra.getClienteId(), compra.getData());

                        List<CompraProduto> produtos = compraProdutoDAO.listarProdutosPorCompra(compra.getId());

                        if (produtos.isEmpty()) {
                            System.out.println("  Nenhum produto vinculado a essa compra.");
                        } else {
                            for (CompraProduto cp : produtos) {
                                System.out.printf("  Produto ID: %d | Quantidade: %d%n", cp.getProdutoId(), cp.getQuantidade());
                            }
                        }
                    }
                }
            }
            case 6 -> {
                System.out.print("Digite o ID do produto: ");
                int produtoId = scanner.nextInt();


                List<CompraProduto> comprasProduto = compraProdutoDAO.listarComprasPorProduto(produtoId);

                if (comprasProduto.isEmpty()) {
                    System.out.println("Este produto não está vinculado a nenhuma compra.");
                } else {
                    System.out.println("Compras que contêm o produto ID " + produtoId + ":");

                    for (CompraProduto cp : comprasProduto) {
                        System.out.printf("Compra ID: %d | Quantidade: %d",
                                cp.getCompraId(), cp.getQuantidade());
                    }
                }
            }
            case 7 -> {
                System.out.print("Digite o ID do registro do produto na compra (ID da tabela compra_produto): ");
                int idRegistro = scanner.nextInt();

                compraProdutoDAO.excluirProdutoCompra(idRegistro);
                System.out.println("Produto removido da compra com sucesso.");
            }

            case 8 -> System.out.println("Saindo do menu de compras.");

            default -> System.out.println("Opção inválida!");
        }

    }
}
