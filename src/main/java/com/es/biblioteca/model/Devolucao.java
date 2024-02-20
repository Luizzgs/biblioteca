package com.es.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Devolucao {
    private String raAluno;
    private int codigoLivro;
    private Date dataDevolucao;
}
