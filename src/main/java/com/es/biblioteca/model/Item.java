package com.es.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

	private Livro livro;
	private Date dataDevolucao;

	public Item(Livro livro) {
		super();
		this.livro = livro;
	}
	
}
