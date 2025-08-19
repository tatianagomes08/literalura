package com.alura.literalura;

import com.alura.literalura.modelos.Autor;
import com.alura.literalura.modelos.Livro;
import com.alura.literalura.repositorios.AutorRepository;
import com.alura.literalura.repositorios.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LiteraluraApplication(AutorRepository autorRepository, LivroRepository livroRepository){
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

	public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        exibirMenu();
    }

    private void exibirMenu() {
        Scanner leitura = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - buscar livro pelo titulo");
            System.out.println("2 - listar livros registrados");
            System.out.println("3 - listar autores registrados");
            System.out.println("4 - listar autores vivos em determinado ano");
            System.out.println("5 - listar livros em um determinado idioma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcaoTexto = leitura.nextLine();
            int opcao;
            try{
                opcao = Integer.parseInt(opcaoTexto);
            }catch (NumberFormatException e){
                System.out.println("Opção inválida!!!");
                continue;
            }
            var cliente = new GutendexCliente();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o titulo do livro desejado: ");
                    var titulo = leitura.nextLine();
                    titulo = titulo.replace(' ', '+');

                    var livroGutendex = cliente.buscaLivroPeloTitulo(titulo);
                    if (livroGutendex == null){
                        System.out.println("Livro não encontrado!");
                        continue;
                    }
                    var livro = new Livro(livroGutendex);

                    if (!Objects.equals(livro.getAutor(), null)){
                        var autor = new Autor(livroGutendex.getAuthors().get(0));
                        var autorExistente = autorRepository.findByNomeIgnoreCase(autor.getNome());
                        if (autorExistente.isPresent()){
                            autor.setId(autorExistente.get().getId());
                        } else {
                            autorRepository.save(autor);
                        }

                        livro.setAutor(autor);
                    }
                    var livroExistente = livroRepository.findByTituloIgnoreCaseAndAutorId(livro.getTitulo(), livro.getAutor().getId());
                    if (livroExistente.isPresent()){
                        livro.setId(livroExistente.get().getId());
                    } else {
                        livroRepository.save(livro);
                    }

                    System.out.println(livro);
                    break;
                case 2:
                    var livros = livroRepository.findAll();
                    var livrosQuantidade = livros.size();
                    var registradosMensagem = " livros registrados.";
                    if (livrosQuantidade == 1){
                        registradosMensagem = " livro registrado.";
                    }
                    System.out.println(livrosQuantidade + registradosMensagem);
                    for (int i = 0; i < livrosQuantidade; i++) {
                        var indiceBase1 = i+1;
                        System.out.println("Livro " + indiceBase1);
                        System.out.println(livros.get(i));
                    }
                    break;
                case 3:
                    var autores = autorRepository.findAll();
                    var autoresQuantidade = autores.size();
                    var autoresRegistradosMensagem = " autores registrados.";
                    if (autoresQuantidade == 1){
                        autoresRegistradosMensagem = " autor registrado.";
                    }
                    System.out.println(autoresQuantidade + autoresRegistradosMensagem);
                    for (int i = 0; i < autoresQuantidade; i++) {
                        var indiceBase1 = i+1;
                        System.out.println("Autor " + indiceBase1);
                        System.out.println(autores.get(i));
                    }
                    break;
                case 4:
                    System.out.println("Digite o ano desejado: ");
                    var anoTexto = leitura.nextLine();
                    int ano;
                    try{
                        ano = Integer.parseInt(anoTexto);
                    }catch (NumberFormatException e){
                        System.out.println("Ano inválido!!!");
                        continue;
                    }
                    var autoresAno = autorRepository.findAutoresVivosEmAno(ano);
                    var autoresAnoQuantidade = autoresAno.size();
                    var anoEncontradoMensagem = " autores encontrados.";
                    if (autoresAnoQuantidade == 1){
                        anoEncontradoMensagem = " autor encontrado.";
                    }
                    System.out.println(autoresAnoQuantidade + anoEncontradoMensagem);
                    for (int i = 0; i < autoresAnoQuantidade; i++) {
                        var indiceBase1 = i+1;
                        System.out.println("Autor " + indiceBase1);
                        System.out.println(autoresAno.get(i));
                    }
                    break;
                case 5:
                    System.out.println("Digite o idioma que deseja consultar os livros: ");
                    var idioma = leitura.nextLine();
                    if(idioma.length() != 2){
                        System.out.println("Idioma precisa ser no formato de 2 letras!");
                        continue;
                    }
                    var livrosIdioma = livroRepository.findByIdiomaIgnoreCase(idioma);
                    var livrosIdiomaQuantidade = livrosIdioma.size();
                    var idiomaEncontradoMensagem = " livros encontrados.";
                    if (livrosIdiomaQuantidade == 1){
                        idiomaEncontradoMensagem = " livro encontrado.";
                    }
                    System.out.println(livrosIdiomaQuantidade + idiomaEncontradoMensagem);
                    for (int i = 0; i < livrosIdioma.size(); i++) {
                        System.out.println("Livro " + i+1);
                        System.out.println(livrosIdioma.get(i));
                    }
                    break;
                case 0:
                    System.out.println("Encerrando aplicação...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        leitura.close();
    }
}
