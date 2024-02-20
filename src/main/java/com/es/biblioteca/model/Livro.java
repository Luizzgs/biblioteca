package com.es.biblioteca.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    private int codigo;
    private String titulo;
    private boolean exemplarBiblioteca;
    private Titulo tituloObj;

    public Livro(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.exemplarBiblioteca = false;
        this.tituloObj = new Titulo(codigo, titulo);
    }

    public Livro(int codigo, String titulo, boolean exemplarBiblioteca) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.exemplarBiblioteca = !exemplarBiblioteca;
        this.tituloObj = new Titulo(codigo, titulo);
    }

}
