package com.es.biblioteca.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Titulo {
	private int prazo;
	private int codigo;
	private String titulo;

	public Titulo (int codigo, String titulo){
		this.codigo = codigo;
		this.prazo = codigo+1;
		this.titulo = titulo;
	}

}