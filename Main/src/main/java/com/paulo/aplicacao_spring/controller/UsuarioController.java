package com.paulo.aplicacao_spring.controller; 

import com.paulo.aplicacao_spring.model.Usuario;
import com.paulo.aplicacao_spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll(); // Busca no H2
    }

    @PostMapping
    public String adicionar(@RequestBody Usuario user) {
        repository.save(user); // Salva no H2
        return "Usuário " + user.getNome() + " cadastrado com sucesso!";
    }

    // Melhore o DELETE para usar o ID do banco em vez do índice da lista
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "Usuário removido!";
    }

    /*private final List<Usuario> usuarios = new ArrayList<>();

    @GetMapping
    public List<Usuario> listar() {
        return usuarios; 
    }

    @PostMapping
    public String adicionar(@RequestBody Usuario user) {
        usuarios.add(user);
        return "Usuário " + user.getNome() + " cadastrado!";
    }

    @PutMapping("/{index}") // O {index} na URL é uma variável
    public String atualizar(@PathVariable int index, @RequestBody Usuario usuarioAtualizado) {
    // Verificando se o índice existe na lista para não dar erro de memória
    if (index >= 0 && index < usuarios.size()) {
        // Pegamos o usuário naquela posição e mudamos o nome dele
        usuarios.get(index).setNome(usuarioAtualizado.getNome());
        return "Usuário no índice " + index + " atualizado para: " + usuarioAtualizado.getNome();
    }
    return "Erro: Índice " + index + " não encontrado!";
    }

    @DeleteMapping("/{index}") // Define que esta rota aceita requisições DELETE
    public String deletar(@PathVariable int index) {
    // Verificamos se o usuário existe para evitar o erro "IndexOutOfBoundsException"
    if (index >= 0 && index < usuarios.size()) {
        Usuario removido = usuarios.remove(index); // Remove e guarda o objeto para exibir o nome
        return "Usuário " + removido.getNome() + " removido com sucesso!";
    }
    return "Erro: Não foi possível encontrar o usuário no índice " + index;
}*/
}