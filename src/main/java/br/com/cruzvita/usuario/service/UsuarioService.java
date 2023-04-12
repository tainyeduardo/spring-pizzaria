package br.com.cruzvita.usuario.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cruzvita.usuario.dto.UsuarioDTO;
import br.com.cruzvita.usuario.model.Usuario;
import br.com.cruzvita.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public ResponseEntity<String> cadastrar(@Valid UsuarioDTO usuario) {

        if(repository.existsByCpf(usuario.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado no sistema!");
        }

        if(repository.existsByTelefone(usuario.getTelefone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Telefone já cadastrado no sistema!");
        }

        Usuario usuarioModel = new Usuario();

        BeanUtils.copyProperties(usuario, usuarioModel);
        repository.save(usuarioModel);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario cadastrado com sucesso!");
    }

    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    public ResponseEntity<String> deletar(UUID id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado no sistema!");
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario deletado com sucesso!");
    }

    public ResponseEntity<String> alterar(Usuario usuario) {
        if(!repository.existsById(usuario.getId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado no sistema!");
        }

        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario alterado com sucesso!");
    }
    
}
