package dao;

import conexao.ConexaoBD;
import model.Categoria;
import model.Cliente;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public void adicionarProduto(String nome, double preco, int quantidade, int idCategoria) {
        try (Connection conn = ConexaoBD.conexao()){
            String sql = "INSERT INTO produto (nome, preco, quantidade, categoria_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, quantidade);
            stmt.setInt(4, idCategoria);

            stmt.executeUpdate();

            System.out.println("Produto criado!");

        } catch (Exception e){
            throw new RuntimeException("Erro ao adicionar produto", e);
        }
    }

    public List<Produto> verProduto() {

        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = ConexaoBD.conexao()){
            String sql = "SELECT p.id AS produto_id, p.nome AS produto_nome, p.preco, p.quantidade, \n" +
                    "               c.id AS categoria_id, c.nome AS categoria_nome\n" +
                    "        FROM produto p\n" +
                    "        JOIN categoria c ON p.categoria_id = c.id;";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                categoria.setNome(rs.getString("categoria_nome"));

                Produto produto = new Produto();
                produto.setId(rs.getInt("produto_id"));
                produto.setNome(rs.getString("produto_nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCategoria(categoria);

                produtos.add(produto);

            }

        } catch (Exception e){
            throw new RuntimeException("Nenhum produto encontrado");
        }
        return produtos;
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                // preencha outros campos se houver
                return produto;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID", e);
        }

        return null;
    }

    public void apagarProduto(int id) {
        try (Connection conn = ConexaoBD.conexao()){
            String sql = "DELETE FROM produto WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0){
                System.out.println("produto deletado com sucesso.");
            } else {
                System.out.println("Nenhum produto encontrado com ID " + id + ".");
            }

        } catch (Exception e){
            throw new RuntimeException("Erro ao deletar produto: " + e.getMessage());
        }
    }

}
