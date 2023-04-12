package br.com.cruzvita.usuario.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cruzvita.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

    boolean existsByCpf(String cpf);

    boolean existsByTelefone(String telefone);
    
}
