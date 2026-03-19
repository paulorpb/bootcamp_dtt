package com.paulobezerra.gerenciador_biblioteca.service;

import com.paulobezerra.gerenciador_biblioteca.dto.EmprestimoResponseDTO;
import com.paulobezerra.gerenciador_biblioteca.dto.LivroResponseDTO;
import com.paulobezerra.gerenciador_biblioteca.dto.UsuarioResponseDTO;
import com.paulobezerra.gerenciador_biblioteca.entity.Emprestimo;
import com.paulobezerra.gerenciador_biblioteca.entity.Livro;
import com.paulobezerra.gerenciador_biblioteca.entity.Usuario;
import com.paulobezerra.gerenciador_biblioteca.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    private EmprestimoResponseDTO toDTO(Emprestimo emprestimo) {
        EmprestimoResponseDTO dto = new EmprestimoResponseDTO();
        dto.setId(emprestimo.getId());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setDataDevolucao(emprestimo.getDataDevolucao());
        dto.setAtivo(emprestimo.getAtivo());

        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        usuarioDTO.setId(emprestimo.getUsuario().getId());
        usuarioDTO.setNome(emprestimo.getUsuario().getNome());
        usuarioDTO.setEmail(emprestimo.getUsuario().getEmail());
        usuarioDTO.setTelefone(emprestimo.getUsuario().getTelefone());
        dto.setUsuario(usuarioDTO);

        LivroResponseDTO livroDTO = new LivroResponseDTO();
        livroDTO.setId(emprestimo.getLivro().getId());
        livroDTO.setTitulo(emprestimo.getLivro().getTitulo());
        livroDTO.setAutor(emprestimo.getLivro().getAutor());
        livroDTO.setIsbn(emprestimo.getLivro().getIsbn());
        livroDTO.setDisponivel(emprestimo.getLivro().getDisponivel());
        dto.setLivro(livroDTO);

        return dto;
    }

    public EmprestimoResponseDTO registrar(Long usuarioId, Long livroId) {
        Usuario usuario = usuarioService.encontrarPorId(usuarioId);
        Livro livro = livroService.encontrarPorId(livroId);

        if (!livro.getDisponivel()) {
            throw new RuntimeException("Livro não está disponível para empréstimo");
        }

        livro.setDisponivel(false);
        livroService.salvarEntidade(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setAtivo(true);

        return toDTO(emprestimoRepository.save(emprestimo));
    }

    public List<EmprestimoResponseDTO> listarTodos() {
        return emprestimoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EmprestimoResponseDTO devolver(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (!emprestimo.getAtivo()) {
            throw new RuntimeException("Este empréstimo já foi encerrado");
        }

        emprestimo.setAtivo(false);
        emprestimo.setDataDevolucao(LocalDate.now());

        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroService.salvarEntidade(livro);

        return toDTO(emprestimoRepository.save(emprestimo));
    }
}