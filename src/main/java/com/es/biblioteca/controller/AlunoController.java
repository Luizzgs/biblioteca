package com.es.biblioteca.controller;

import com.es.biblioteca.model.Aluno;
import com.es.biblioteca.dao.AlunoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    private AlunoDAO AlunoDAO = new AlunoDAO();


    @GetMapping("/listar")
    public String listarAlunos(Model model) {
        List<Aluno> alunos = AlunoDAO.obterTodosAlunos();

        if (alunos == null) {
            alunos = new ArrayList<>();
        }

        model.addAttribute("alunos", alunos);
        return "listaAlunos";
    }

    // Exibe o formulário de cadastro
    @GetMapping("/cadastrar")
    public String exibirFormulario(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "cadastrarAluno";
    }

    // Processa o formulário de cadastro
    @PostMapping("/cadastrar")
    public String cadastrarAluno(@ModelAttribute Aluno aluno, Model model) {
        boolean cadastrou = AlunoDAO.inserirAluno(aluno.getRa(), aluno.getNome());

        if (cadastrou) {
            model.addAttribute("mensagem", "Aluno cadastrado com sucesso!");
        } else {
            model.addAttribute("mensagem", "Ocorreu um erro ao cadastrar o aluno.");
        }

        return "resultadoCadastro";
    }


}
