package com.es.biblioteca.model;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    private Date dataEmprestimo = new Date();
    private Date dataPrevista = new Date();
    private Item item = new Item();
    private Aluno aluno;
    private Livro livro;
	private int id;


public Emprestimo(Aluno aluno, Livro livro) {
        this.aluno = aluno;
        this.livro = livro;
    }
    public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
        this.dataPrevista = dataPrevistaDevolucao;
    }
}
