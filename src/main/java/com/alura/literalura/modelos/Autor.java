package com.alura.literalura.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private int anoNascimento;
    private int anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros;

    public Autor(){}

    public Autor(AutorResponse autorResponse){
        nome = autorResponse.getName();
        anoNascimento = autorResponse.getBirth_year();
        anoFalecimento = autorResponse.getDeath_year();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoDeNascimento) {
        this.anoNascimento = anoDeNascimento;
    }

    public int getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(int anoDeFalecimento) {
        this.anoFalecimento = anoDeFalecimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "----- Autor -----\n" +
                " Nome: " + nome +
                "\n Ano de Nascimento: " + anoNascimento +
                "\n Ano de Falecimento: " + anoFalecimento +
                "\n-----------------";
    }
}
