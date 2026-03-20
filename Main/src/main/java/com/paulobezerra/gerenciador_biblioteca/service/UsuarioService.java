package com.paulobezerra.gerenciador_biblioteca.service;

import com.paulobezerra.gerenciador_biblioteca.dto.UsuarioRequestDTO;
import com.paulobezerra.gerenciador_biblioteca.dto.UsuarioResponseDTO;
import com.paulobezerra.gerenciador_biblioteca.entity.Usuario;
import com.paulobezerra.gerenciador_biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        return dto;
    }

    private Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        return usuario;
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        return toDTO(usuarioRepository.save(toEntity(dto)));
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        return toDTO(encontrarPorId(id));
    }

    public Usuario encontrarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = encontrarPorId(id);
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        return toDTO(usuarioRepository.save(usuario));
    }

    public void deletar(Long id) {
        encontrarPorId(id);
        usuarioRepository.deleteById(id);
    }
}