package service;

import dao.CompraDAO;
import dao.CompraProdutoDAO;
import model.Compra;
import model.CompraProduto;

import java.time.LocalDateTime;
import java.util.Map;

public class CompraService {
    private CompraDAO compraDAO = new CompraDAO();
    private CompraProdutoDAO compraProdutoDAO = new CompraProdutoDAO();

    public void registrarVenda(int clienteId, Map<Integer, Integer> produtos) {

        Compra compra = new Compra();
        compra.setClienteId(clienteId);
        compra.setData(LocalDateTime.now());

        compraDAO.adicionarCompra(compra.getId());// aqui o ID da compra é gerado e setado na entidade


        for (Map.Entry<Integer, Integer> entry : produtos.entrySet()) {
            int produtoId = entry.getKey();
            int quantidade = entry.getValue();

            CompraProduto cp = new CompraProduto();
            cp.setCompraId(compra.getId()); // pega o ID gerado da compra
            cp.setProdutoId(produtoId);
            cp.setQuantidade(quantidade);

            compraProdutoDAO.adicionarProdutoCompra(cp);
        }

        System.out.println("Venda registrada com sucesso!");
    }
}
