package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static final String SERVIDOR = "jdbc:mysql://localhost:3306/exemplo_alunos";
    public static final String USUARIO = "root";
    public static final String SENHA = "password";

    public Connection getConexao() {
        try {
            return DriverManager.getConnection(SERVIDOR, USUARIO, SENHA);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
