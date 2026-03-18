package com.paulobezerra.gerenciador_biblioteca.repository;

import com.paulobezerra.gerenciador_biblioteca.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}