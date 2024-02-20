package com.es.biblioteca.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class Aluno {

	private String ra;
	private String nome;
	private boolean debito;

	public Aluno(String ra, String nome) {
		this.ra = ra;
		this.nome = nome;
		this.debito = false;
	}

}
