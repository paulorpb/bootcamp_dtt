package com.paulobezerra.gerenciador_biblioteca.service;

import com.paulobezerra.gerenciador_biblioteca.entity.Emprestimo;
import com.paulobezerra.gerenciador_biblioteca.entity.Livro;
import com.paulobezerra.gerenciador_biblioteca.entity.Usuario;
import com.paulobezerra.gerenciador_biblioteca.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public Emprestimo registrar(Long usuarioId, Long livroId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        Livro livro = livroService.buscarPorId(livroId);

        if (!livro.getDisponivel()) {
            throw new RuntimeException("Livro não está disponível para empréstimo");
        }

        livro.setDisponivel(false);
        livroService.salvar(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setAtivo(true);

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo devolver(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (!emprestimo.getAtivo()) {
            throw new RuntimeException("Este empréstimo já foi encerrado");
        }

        emprestimo.setAtivo(false);
        emprestimo.setDataDevolucao(LocalDate.now());

        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroService.salvar(livro);

        return emprestimoRepository.save(emprestimo);
    }
}