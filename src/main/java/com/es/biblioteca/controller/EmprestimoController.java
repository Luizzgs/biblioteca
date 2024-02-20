package com.es.biblioteca.controller;

import com.es.biblioteca.model.Emprestimo;
import com.es.biblioteca.model.Livro;
import com.es.biblioteca.dao.AlunoDAO;
import com.es.biblioteca.dao.LivroDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.es.biblioteca.dao.EmprestimoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private AlunoDAO alunoDAO = new AlunoDAO();
    private LivroDAO livroDAO = new LivroDAO();
    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    @GetMapping("/listar")
    public String listarEmprestimos(Model model) {
        List<Emprestimo> emprestimos = EmprestimoDAO.obterTodosEmprestimos();
        if (emprestimos == null) {
            emprestimos =  new ArrayList<>();
        }
        model.addAttribute("emprestimos", emprestimos);
        return "listaEmprestimos";
    }

    @GetMapping("/cadastrar")
    public String exibirFormulario(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        return "cadastrarEmprestimo";
    }


    @PostMapping("/cadastrar")
    public String cadastrarEmprestimo(String ra, int codigoLivro , Model model) throws SQLException {

        if (!alunoDAO.verificarAlunoCadastrado(ra)){
            model.addAttribute("mensagem", "Aluno não cadastrado.");
            return "resultadoCadastro";
        }
        if (!livroDAO.verificarLivroCadastrado(codigoLivro)) {
            model.addAttribute("mensagem", "Livro não cadastrado.");
            return "resultadoCadastro";
        }
        if (!livroDAO.verificaDisponibilidade(codigoLivro)) {
            model.addAttribute("mensagem", "Livro indisponível.");
            return "resultadoCadastro";
        }


        boolean cadastrou = EmprestimoDAO.inserirEmprestimo(ra, codigoLivro);

        if (cadastrou) {
            model.addAttribute("mensagem", "Emprestimo cadastrado com sucesso!");
            Livro livro = livroDAO.buscarLivroPorCodigo(codigoLivro);
            livroDAO.marcarLivroComoIndisponivel(codigoLivro);
        } else {
            model.addAttribute("mensagem", "Ocorreu um erro ao cadastrar o emprestimo.");
        }
        return "resultadoCadastro";
    }

    @GetMapping("/devolver")
    public String exibirFormularioDevolucao(Model model) {
        List<Emprestimo> emprestimos = EmprestimoDAO.obterTodosEmprestimos();
        if (emprestimos == null) {
            emprestimos =  new ArrayList<>();
        }
        model.addAttribute("emprestimos", emprestimos);
        return "devolverLivro";
    }



    @PostMapping("/devolver")
    public String devolverLivro(@RequestParam Long emprestimoId, Model model) throws SQLException {
        Emprestimo emprestimo = EmprestimoDAO.buscarEmprestimoPorId(Math.toIntExact(emprestimoId));

        Livro livro = livroDAO.buscarLivroPorCodigo(emprestimo.getLivro().getCodigo());
        boolean devolveu = EmprestimoDAO.devolverLivro(emprestimoId);
        if (devolveu) {
            livroDAO.marcarLivroComoDisponivel(livro.getCodigo());
            model.addAttribute("mensagem", "Livro devolvido com sucesso!");
        } else {
            model.addAttribute("mensagem", "Ocorreu um erro ao processar a devolução.");
        }
        return "resultadoCadastro";
    }




}
