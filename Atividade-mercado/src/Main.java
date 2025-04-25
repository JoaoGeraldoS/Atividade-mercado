import Interface.MenuPrincipal;
import tabelas.Tabelas;
import conexao.ConexaoBD;

public class Main {
    public static void main(String[] args) {

        ConexaoBD conexaoBD = new ConexaoBD();

        conexaoBD.conexao();

        Tabelas tabelas = new Tabelas();
//        tabelas.criaTabelas();

        MenuPrincipal menuPrincipal= new MenuPrincipal();
        menuPrincipal.menuPrincipal();





    }
}
