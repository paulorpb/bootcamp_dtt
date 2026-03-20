package com.paulobezerra.gerenciador_biblioteca.service;

import com.paulobezerra.gerenciador_biblioteca.dto.LivroRequestDTO;
import com.paulobezerra.gerenciador_biblioteca.dto.LivroResponseDTO;
import com.paulobezerra.gerenciador_biblioteca.entity.Livro;
import com.paulobezerra.gerenciador_biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    private LivroResponseDTO toDTO(Livro livro) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setIsbn(livro.getIsbn());
        dto.setDisponivel(livro.getDisponivel());
        return dto;
    }

    private Livro toEntity(LivroRequestDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setDisponivel(true);
        return livro;
    }

    public LivroResponseDTO salvar(LivroRequestDTO dto) {
        return toDTO(livroRepository.save(toEntity(dto)));
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO buscarPorId(Long id) {
        return toDTO(encontrarPorId(id));
    }

    public Livro encontrarPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = encontrarPorId(id);
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        return toDTO(livroRepository.save(livro));
    }

    public Livro salvarEntidade(Livro livro) {
        return livroRepository.save(livro);
    }

    public void deletar(Long id) {
        encontrarPorId(id);
        livroRepository.deleteById(id);
    }
}