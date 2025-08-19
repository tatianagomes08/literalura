package com.alura.literalura;

import com.alura.literalura.modelos.LivroResponse;
import com.alura.literalura.modelos.LivrosBuscaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexCliente {
    private final ObjectMapper mapper = new ObjectMapper();

    public LivroResponse buscaLivroPeloTitulo(String titulo) {

        // Cria o cliente HTTP
        HttpClient client = HttpClient.newBuilder()
                .build();

        // Cria a requisição GET
        String url = "https://gutendex.com/books/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?search=" + titulo))
                .GET()
                .build();

        try {
            // Envia a requisição e recebe a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Retorna o corpo da resposta
            var body = response.body();
            if (body.isEmpty()){
                return null;
            }
            var livros = mapper.readValue(body, LivrosBuscaResponse.class);
            if (livros.getResults() == null || livros.getResults().isEmpty()){
                return null;
            }
            return livros.getResults().get(0);

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao fazer requisição: " + e.getMessage());
            return null;
        }
    }
}