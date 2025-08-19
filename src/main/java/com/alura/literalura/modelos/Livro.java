package com.alura.literalura.modelos;

import jakarta.persistence.*;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String idioma;
    private Integer numeroDownloads;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro() {}

    public Livro(LivroResponse livroResponse) {
        titulo = livroResponse.getTitle();

        if (livroResponse.getAuthors() == null || livroResponse.getAuthors().isEmpty()){
            autor = null;
        }
        else{
            autor = new Autor(livroResponse.getAuthors().get(0));
        }

        idioma = livroResponse.getLanguages().get(0);
        numeroDownloads = livroResponse.getDownload_count();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(int numeroDeDownloads) {
        this.numeroDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {
        var nomeAutor = "";
        if (autor != null){
            nomeAutor = autor.getNome();
        }
        return "----- Livro -----\n" +
                " Título: " + titulo +
                "\n Autor: " + nomeAutor +
                "\n Idioma: " + idioma +
                "\n Número de Downloads: " + numeroDownloads +
                "\n-----------------";
    }
}
