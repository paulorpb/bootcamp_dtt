package com.paulobezerra.gerenciador_biblioteca.repository;

import com.paulobezerra.gerenciador_biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}