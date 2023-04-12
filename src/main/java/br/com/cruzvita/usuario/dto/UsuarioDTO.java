package br.com.cruzvita.usuario.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioDTO {
    
    @NotBlank(message = "Preencha o nome!")
    private String nome;

    @NotBlank(message = "Preencha o CPF!")
    private String cpf;

    @NotBlank(message = "Preencha o Telefone!")
    private String telefone;
}
