
package funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class conexao {
    
    private String url;
    private String usuario;
    private String senha;
    private Connection connection;
    
    conexao(){
        url = "jdbc:postgresql://localhost:5432/crud";
        usuario = "postgres";
        senha = "william2209";
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
            
        } catch (Exception e) {
            //mostra na tela se ouver erro
            e.printStackTrace();
        }
    }
    
    public int executaSql(String sql) {
        
        try {
            Statement stm = connection.createStatement();
            int res = stm.executeUpdate(sql);
            connection.close();
            return res;
            
        } catch (Exception e) {
            //mostra na tela se ouver erro
            e.printStackTrace();
            return 0;
        }
    }
    
    public ResultSet executaBusca(String sql){
        
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            connection.close();
            return rs;
                
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    
    public String SQLSemFiltro() {

        String sql = "select * from funcionario\n"
                + "inner JOIN cargo\n"
                + "ON funcionario.id_cargo = cargo.id";

        return sql;
    }

    public String SQLFiltroNome(String nome) {

        nome = "'" + nome + "'";
        String sql = "select * from funcionario\n"
                + "inner JOIN cargo\n"
                + "ON funcionario.id_cargo = cargo.id and funcionario.nome =" + nome;

        return sql;
    }
    
    public String SQLFiltroCargo(String cargo) {
        
        String idCargo = null;       
        
        switch (cargo) {

            case "Estagiario":
                idCargo = "'1'";
                break;
            case "Auxiliar":
                idCargo = "2";
                break;
            case "Funcionario":
                idCargo = "3";
                break;
            case "Gerente":
                idCargo = "4";
                break;
        }
        
        String sql = "select * from funcionario\n" +
                     "inner JOIN cargo\n" +
                     "ON funcionario.id_cargo = cargo.id and cargo.id = " + idCargo;

        return sql;
    }
    
    public String SQLFiltroNomeCargo(String nome,String cargo) {
        
        String idCargo = null;
        nome = "'" + nome + "'";
        
        switch (cargo) {

            case "Estagiario":
                idCargo = "'1'";
                break;
            case "Auxiliar":
                idCargo = "2";
                break;
            case "Funcionario":
                idCargo = "3";
                break;
            case "Gerente":
                idCargo = "4";
                break;
        }
        
        String sql = "select * from funcionario\n" +
                     "inner JOIN cargo\n" +
                     "ON funcionario.id_cargo = cargo.id and funcionario.nome =" + nome + "and cargo.id =" + idCargo;

        return sql;
    }
    

    PreparedStatement PreparedStatement(String asd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
} //Fim de td


