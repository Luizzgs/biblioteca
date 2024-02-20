package com.es.biblioteca.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Debito {
	int codigoAluno;
	public Debito(int aluno){
		this.codigoAluno = aluno;
	}

}