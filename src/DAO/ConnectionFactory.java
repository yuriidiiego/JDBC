package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    
    private ConnectionFactory() {
        throw new UnsupportedOperationException();
    }

    //Método que retorna uma conexão com o banco de dados.
    public static Connection getConnection() {
        String servidor = "jdbc:sqlserver://localhost:1433;databaseName=digital_innovation_one";
        String usuario = "sa";
        String senha = "959870yay";

        Connection conexao = null;

        try {
            conexao = DriverManager.getConnection(servidor, usuario, senha);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao estabelecer conexão com o banco de dados!");
        }
        return conexao;
    }
}
