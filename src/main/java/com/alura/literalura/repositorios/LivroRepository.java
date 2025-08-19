package com.alura.literalura.repositorios;

import com.alura.literalura.modelos.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    List<Livro> findByIdiomaIgnoreCase(String idioma);
    Optional<Livro> findByTituloIgnoreCaseAndAutorId(String titulo, Integer autorId);

}
