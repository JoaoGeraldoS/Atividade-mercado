package dao;

import conexao.ConexaoBD;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void criarCliente(Cliente cliente) {
        try (Connection conn = ConexaoBD.conexao()){
            String sql = "INSERT INTO cliente (nome, cpf) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());

            stmt.executeUpdate();

        } catch (Exception e){
            throw new RuntimeException("Erro ao registrar cliente ", e);
        }
    }

    public List<Cliente> verClientes() {

        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = ConexaoBD.conexao()){
            String sql = "SELECT * FROM cliente";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                clientes.add(cliente);

            };

        } catch (Exception e){
            throw new RuntimeException("Nenhum cliente encontrado");
        }
        return clientes;
    }


    public void apagarCliente(int id) {
        try (Connection conn = ConexaoBD.conexao()){
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0){
                System.out.println("Cliente deletado com sucesso.");
            } else {
                System.out.println("Nenhum cliente encontrado com ID " + id + ".");
            }

        } catch (Exception e){
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}
