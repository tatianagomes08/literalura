package com.alura.literalura.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivrosBuscaResponse {
    private List<LivroResponse> results;

    public List<LivroResponse> getResults() {
        return results;
    }

    public void setResults(List<LivroResponse> results) {
        this.results = results;
    }
}
