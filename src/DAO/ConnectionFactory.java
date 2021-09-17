package DAO;

import java.io.IOException;
import java.sql.*;

public class ConnectionFactory {

    //construtor declarado como privado. Evitando assim criar instancias da classe.
    private ConnectionFactory() {
        throw new UnsupportedOperationException(); //Lançado para indicar que a operação solicitada não é compatível.
    }

    //método pra conexão com o Banco de Dados
    public static Connection getConnection() {
        String servidor = "jdbc:sqlserver://localhost:1433;databaseName=digital_innovation_one";
        String usuario = "sa";
        String senha = "959870yay";

        Connection conexao = null;

        try {
            conexao = DriverManager.getConnection(servidor, usuario, senha);
            System.out.println("Conexão funcionado!!");

        } catch (SQLException e) {
            System.out.println("FALHA ao tentar conectar");
        }
        return conexao;
    }
}
