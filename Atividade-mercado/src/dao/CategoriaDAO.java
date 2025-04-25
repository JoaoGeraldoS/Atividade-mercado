package dao;

import conexao.ConexaoBD;
import model.Categoria;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public Categoria adicionarCategoria(String nome){
        try (Connection conn = ConexaoBD.conexao()){
            String sql = "INSERT INTO categoria (nome) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Categoria categoria = new Categoria();

            stmt.setString(1, nome);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                int idGerado = rs.getInt(1);
                categoria.setId(idGerado);
            }
            return categoria;

        } catch (Exception e){
            throw new RuntimeException("Erro ao registrar categoria ", e);
        }
    }

    public List<Categoria> verCategoria() {

        List<Categoria> categorias = new ArrayList<>();

        try (Connection conn = ConexaoBD.conexao()){
            String sql = "SELECT * FROM categoria";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));

                categorias.add(categoria);

            };

        } catch (Exception e){
            throw new RuntimeException("Nenhuma categoria encontrado");
        }
        return categorias;
    }

    public Categoria buscarCategoria(String nome){
        try (Connection conn = ConexaoBD.conexao()) {
            String sql = "SELECT id, nome FROM categoria WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                return categoria;
            } else {
                throw new RuntimeException("Categoria não encontrada");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar categoria", e);
        }
    }


    public void apagarCategoria(int id) {
        try (Connection conn = ConexaoBD.conexao()){
            String sql = "DELETE FROM categoria WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0){
                System.out.println("Categoria deletado com sucesso.");
            } else {
                System.out.println("Nenhuma categoria encontrada com ID " + id + ".");
            }

        } catch (Exception e){
            throw new RuntimeException("Erro ao deletar categoria: " + e.getMessage());
        }
    }

}
