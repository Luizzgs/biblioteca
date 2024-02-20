package com.es.biblioteca.controller;

import com.es.biblioteca.model.Livro;
import com.es.biblioteca.dao.LivroDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livro")
public class LivroController {

    private LivroDAO LivroDAO = new LivroDAO();


    @GetMapping("/listar")
    public String listarLivros(Model model) {
        List<Livro> livros = LivroDAO.obterTodosLivros();

        if (livros == null) {
            livros =  new ArrayList<>();
        }

        model.addAttribute("livros", livros);
        return "listaLivros";
    }

    @GetMapping("/cadastrar")
    public String exibirFormulario(Model model) {
        model.addAttribute("livro", new Livro());
        return "cadastrarLivro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarLivro(@ModelAttribute Livro livro, Model model) {
        boolean cadastrou = LivroDAO.inserirLivro(livro.getTitulo(), livro.getCodigo());

        if (cadastrou) {
            model.addAttribute("mensagem", "Livro cadastrado com sucesso!");
        } else {
            model.addAttribute("mensagem", "Ocorreu um erro ao cadastrar o livro.");
        }

        return "resultadoCadastro";
    }




}