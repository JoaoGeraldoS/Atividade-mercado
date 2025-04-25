package dao;

import conexao.ConexaoBD;
import model.CompraProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CompraProdutoDAO {
    public void adicionarProdutoCompra(CompraProduto compraProduto) {
        String sql = "INSERT INTO compra_produto (compra_id, produto_id, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, compraProduto.getCompraId());
            stmt.setInt(2, compraProduto.getProdutoId());
            stmt.setInt(3, compraProduto.getQuantidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar produto à compra", e);
        }
    }


    public List<CompraProduto> listarProdutosPorCompra(int compraId) {
        String sql = "SELECT * FROM compra_produto WHERE compra_id = ?";
        List<CompraProduto> produtosCompra = new ArrayList<>();

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, compraId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CompraProduto compraProduto = new CompraProduto();
                compraProduto.setCompraId(rs.getInt("compra_id"));
                compraProduto.setProdutoId(rs.getInt("produto_id"));
                compraProduto.setQuantidade(rs.getInt("quantidade"));

                produtosCompra.add(compraProduto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos da compra", e);
        }

        return produtosCompra;
    }

    public List<CompraProduto> listarComprasPorProduto(int produtoId) {
        String sql = "SELECT * FROM compra_produto WHERE produto_id = ?";
        List<CompraProduto> comprasProduto = new ArrayList<>();

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, produtoId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CompraProduto compraProduto = new CompraProduto();
                compraProduto.setCompraId(rs.getInt("compra_id"));
                compraProduto.setProdutoId(rs.getInt("produto_id"));
                compraProduto.setQuantidade(rs.getInt("quantidade"));

                comprasProduto.add(compraProduto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar compras do produto", e);
        }

        return comprasProduto;
    }

    public void excluirProdutoCompra(int id) {
        String sql = "DELETE FROM compra_produto WHERE id = ?";

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto da compra", e);
        }
    }
}
