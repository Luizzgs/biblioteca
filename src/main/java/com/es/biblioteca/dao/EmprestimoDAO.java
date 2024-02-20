package com.es.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.es.biblioteca.bd.ConexaoBD;
import com.es.biblioteca.model.Aluno;
import com.es.biblioteca.model.Emprestimo;
import com.es.biblioteca.model.Livro;

public class EmprestimoDAO {

    public static Emprestimo buscarEmprestimoPorId(int id) {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Emprestimo emprestimo = new Emprestimo();
                    emprestimo.setId(rs.getInt("id"));
                    emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                    emprestimo.setDataPrevistaDevolucao(rs.getDate("data_prevista_devolucao"));

                    Aluno aluno = new AlunoDAO().buscarAlunoPorRa(rs.getString("ra_aluno"));
                    emprestimo.setAluno(aluno);

                    Livro livro = new LivroDAO().buscarLivroPorCodigo(rs.getInt("codigo_livro"));
                    emprestimo.setLivro(livro);

                    return emprestimo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static boolean inserirEmprestimo(String ra, int codigoLivro) {
        String sql = "INSERT INTO emprestimo (ra_aluno, codigo_livro, data_emprestimo, data_prevista_devolucao) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ra);
            stmt.setInt(2, codigoLivro);
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now().plusDays(7)));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarAtraso(String raAluno, int codigoLivro) {
        String sql = "SELECT prazo_emprestimo FROM livros WHERE ra_aluno = ? AND codigo_livro = ?";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, raAluno);
            stmt.setInt(2, codigoLivro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LocalDate dataPrevistaDevolucao = rs.getDate("data_prevista_devolucao").toLocalDate();
                    LocalDate dataDevolucao = LocalDate.now();

                    return dataDevolucao.isAfter(dataPrevistaDevolucao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static List<Emprestimo> obterTodosEmprestimos(){
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM Emprestimo";

        try(Connection conn = ConexaoBD.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataPrevistaDevolucao(rs.getDate("data_prevista_devolucao"));
                emprestimo.getItem().setDataDevolucao(rs.getDate("data_devolucao"));


                // Carregar aluno associado ao empréstimo
                String raAluno = rs.getString("ra_aluno");
                Aluno aluno = new AlunoDAO().buscarAlunoPorRa(raAluno);
                emprestimo.setAluno(aluno);

                // Carregar livro associado ao empréstimo
                int codigoLivro = rs.getInt("codigo_livro");
                Livro livro = new LivroDAO().buscarLivroPorCodigo(codigoLivro);
                emprestimo.setLivro(livro);

                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    public static boolean devolverLivro(Long emprestimoId) {
        String sql = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(2, emprestimoId);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}