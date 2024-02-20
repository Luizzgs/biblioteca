package com.es.biblioteca.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    //configuracoes do bd
    private static final String URL = "jdbc:postgresql://localhost:5432/Biblioteca";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "admin";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static void fecharConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
