package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String url = "jdbc:postgresql://dpg-d04nb515pdvs73a8nk70-a/mercadodb_53ll";
    private static final String username = "mercadodb_53ll_user";
    private static final String password = "ag1bLTWUJaCCRqWz6zxNdh1uIq6WdGQS";

    public static Connection conexao(){
        try {

            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e){
            throw new RuntimeException("Erro ao conectar com o banco.", e);
        }
    }

}
