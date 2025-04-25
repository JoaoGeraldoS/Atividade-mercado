package tabelas;

import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.Statement;

public class Tabelas {
    public static void criaTabelas() {
        try (Connection conn = ConexaoBD.conexao();
            Statement stmt = conn.createStatement()){

            stmt.execute(
                """
                        CREATE TABLE IF NOT EXISTS categoria (
                            id SERIAL PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL
                        );
                        CREATE TABLE IF NOT EXISTS produto (
                            id SERIAL PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            preco DECIMAL(10, 2) NOT NULL,
                            quantidade INT NOT NULL,
                            categoria_id INT NOT NULL,
                            FOREIGN KEY (categoria_id) REFERENCES categoria(id)
                        );
                        CREATE TABLE IF NOT EXISTS cliente (
                            id SERIAL PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            cpf VARCHAR(14) UNIQUE NOT NULL
                        );
                        CREATE TABLE IF NOT EXISTS compra (
                            id SERIAL PRIMARY KEY,
                            cliente_id INT NOT NULL,
                            data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (cliente_id) REFERENCES cliente(id)
                        );
                        CREATE TABLE IF NOT EXISTS compra_produto (
                            compra_id INT NOT NULL,
                            produto_id INT NOT NULL,
                            quantidade INT NOT NULL,
                            PRIMARY KEY (compra_id, produto_id),
                            FOREIGN KEY (compra_id) REFERENCES compra(id),
                            FOREIGN KEY (produto_id) REFERENCES produto(id)
                        );
                   
                    """
            );
            System.out.println("Tabelas criadas!");
        } catch (Exception e){
            throw new RuntimeException("Erro ao criar tabelas ", e);
        }
    }
}
