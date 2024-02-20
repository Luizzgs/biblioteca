package com.es.biblioteca.dao;

import com.es.biblioteca.bd.ConexaoBD;
import com.es.biblioteca.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LivroDAO {

    public static boolean inserirLivro(String titulo, int id) {
        String sql = "INSERT INTO livros (titulo, id, disponivel) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setInt(2, id);
            stmt.setBoolean(3, true);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void marcarLivroComoDisponivel(int codigoLivro) throws SQLException {
        String sql = "UPDATE livros SET disponivel = ?, prazo_emprestimo = NULL WHERE id = ?";
        try (Connection connection = ConexaoBD.obterConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, true);
            statement.setInt(2, codigoLivro);
            statement.executeUpdate();
        }
    }

    public static void marcarLivroComoIndisponivel(int codigoLivro) throws SQLException {
        String sql = "UPDATE livros SET disponivel = ?, prazo_emprestimo = NULL WHERE id = ?";
        try (Connection connection = ConexaoBD.obterConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, false);
            statement.setInt(2, codigoLivro);
            statement.executeUpdate();
        }
    }


    public Livro buscarLivroPorCodigo(int codigo) {
        Livro livro = null;
        String sql = "SELECT * FROM livros WHERE id = ?";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String titulo = rs.getString("titulo");
                    livro = new Livro(codigo, titulo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    public boolean verificaDisponibilidade(int codigoLivro) {
        boolean disponivel = false;
        String sql = "SELECT disponivel FROM livros WHERE id = ?";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigoLivro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    disponivel = rs.getBoolean("disponivel");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disponivel;
    }


    public static boolean verificarLivroCadastrado(int id) {
        String sql = "SELECT COUNT(*) FROM livros WHERE id = ?";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static List<Livro> obterTodosLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"));
                livro.setExemplarBiblioteca(rs.getBoolean("disponivel"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return livros;
    }
}