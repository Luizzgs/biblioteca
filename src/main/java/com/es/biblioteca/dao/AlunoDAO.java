package com.es.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.es.biblioteca.bd.ConexaoBD;
import com.es.biblioteca.model.Aluno;

public class AlunoDAO {
    public static boolean inserirAluno(String ra, String nome) {
        String sql = "INSERT INTO alunos (ra, nome, debito) VALUES (?, ?, ?)";
        try (Connection connection = ConexaoBD.obterConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ra);
            preparedStatement.setString(2, nome);
            preparedStatement.setBoolean(3, false);
            int response = preparedStatement.executeUpdate();
            return response > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void criarDebito(String raAluno) {
        String sql = "UPDATE alunos SET debito = ? WHERE ra = ?";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, true);
            stmt.setString(2, raAluno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificarAlunoCadastrado(String ra) {
        String sql = "SELECT COUNT(*) FROM alunos WHERE ra = ?";
        try (Connection conn = ConexaoBD.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ra);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean verificarDebitosAluno(String ra) {
        String sql = "SELECT debito FROM alunos WHERE ra = ?";
        try (Connection conn = ConexaoBD.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ra);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                boolean debito = resultSet.getBoolean("debito");
                return debito;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Aluno buscarAlunoPorRa(String ra) {
        Aluno aluno = null;
        String sql = "SELECT * FROM alunos WHERE ra = ?";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ra);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno(rs.getString("ra"), rs.getString("nome"));
                    aluno.setDebito(rs.getBoolean("debito"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }

    public static List<Aluno> obterTodosAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getString("ra"), rs.getString("nome"));
                aluno.setDebito(rs.getBoolean("debito"));
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }



}
