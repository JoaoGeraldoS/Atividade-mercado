package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String url = "jdbc:postgresql://dpg-d05oo6idbo4c73f9jnqg-a.oregon-postgres.render.com/dbmercado_grzp";
    private static final String username = "mercado";
    private static final String password = "bVdtq0vNL8bByRl3ctGW6vSuV218KsZX";

    public static Connection conexao(){
        try {

            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e){
            throw new RuntimeException("Erro ao conectar com o banco.", e);
        }
    }

}
