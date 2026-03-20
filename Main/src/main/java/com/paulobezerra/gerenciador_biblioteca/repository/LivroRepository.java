package com.paulobezerra.gerenciador_biblioteca.repository;

import com.paulobezerra.gerenciador_biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}