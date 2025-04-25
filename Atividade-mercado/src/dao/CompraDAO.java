package dao;

import conexao.ConexaoBD;
import model.Compra;



import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    public Compra adicionarCompra(int idCliente) {
        String sql = "INSERT INTO compra (cliente_id, data) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Compra compra = new Compra();

            compra.setData(LocalDateTime.now());

            stmt.setInt(1, idCliente);
            stmt.setTimestamp(2, Timestamp.valueOf(compra.getData()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                compra.setId(idGerado);
            }
            return compra;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar compra", e);
        }
    }

    public Compra buscarCompraPorId(int id) {
        String sql = "SELECT * FROM compra WHERE id = ?";
        Compra compra = null;

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setClienteId(rs.getInt("cliente_id"));
                compra.setData(rs.getTimestamp("data").toLocalDateTime());

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar compra", e);
        }

        return compra;
    }


    public List<Compra> listarCompras() {
        String sql = "SELECT * FROM compra";
        List<Compra> compras = new ArrayList<>();

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setClienteId(rs.getInt("cliente_id"));
                compra.setData(rs.getTimestamp("data").toLocalDateTime());

                compras.add(compra);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar compras", e);
        }

        return compras;
    }

    public void atualizarCompra(Compra compra) {
        String sql = "UPDATE compra SET cliente_id = ?, data = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, compra.getClienteId());
            stmt.setTimestamp(2, Timestamp.valueOf(compra.getData()));
            stmt.setInt(3, compra.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar compra", e);
        }
    }

    public void excluirCompra(int id) {
        String sql = "DELETE FROM compra WHERE id = ?";

        try (Connection conn = ConexaoBD.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir compra", e);
        }
    }
}
