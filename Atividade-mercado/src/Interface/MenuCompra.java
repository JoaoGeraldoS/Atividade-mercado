package Interface;

import dao.CompraDAO;
import dao.CompraProdutoDAO;
import dao.ProdutoDAO;
import model.Compra;
import model.CompraProduto;
import model.Produto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuCompra extends GenericsMenu{
    private final Map<Integer, Runnable> opcoes = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    private final CompraDAO compraDAO = new CompraDAO();
    private final CompraProdutoDAO compraProdutoDAO = new CompraProdutoDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public MenuCompra() {
        opcoes.put(1, this::registraCompra);
        opcoes.put(2, this::listarCompras);
        opcoes.put(3, this::compraEspecifica);
        opcoes.put(4, this::iniciarCompra);
        opcoes.put(5, this::listarCompraProduto);
        opcoes.put(6, this::verComprasDeProdutos);
        opcoes.put(7, this::apagarCompra);
    }

    public void menuCompra() {
        int opcao;

        do {
            genericMenuCompra();

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 8) {
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

    private void registraCompra() {
        System.out.print("Digite o ID do cliente: ");
        int idCliente = scanner.nextInt();
        Compra novaCompra = compraDAO.adicionarCompra(idCliente);
        System.out.println("Compra registrada com ID: " + novaCompra.getId());
    }

    private void listarCompras() {
        List<Compra> compras = compraDAO.listarCompras();
        if (compras.isEmpty()) {
            System.out.println("Nenhuma compra encontrada.");
        } else {
            for (Compra compra : compras) {
                System.out.printf("ID: %d | Cliente ID: %d | Data: %s%n", compra.getId(), compra.getClienteId(),
                        compra.getData());
            }
        }
    }

    private void compraEspecifica() {
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

    private void iniciarCompra() {
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

    private void listarCompraProduto() {
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

    private void verComprasDeProdutos() {
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

    private void apagarCompra() {
        System.out.print("Digite o ID do registro do produto na compra (ID da tabela compra_produto): ");
        int idRegistro = scanner.nextInt();

        compraProdutoDAO.excluirProdutoCompra(idRegistro);
        System.out.println("Produto removido da compra com sucesso.");
    }
}
