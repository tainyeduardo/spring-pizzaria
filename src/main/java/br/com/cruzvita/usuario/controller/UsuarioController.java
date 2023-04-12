package br.com.cruzvita.usuario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.com.cruzvita.usuario.dto.UsuarioDTO;
import br.com.cruzvita.usuario.model.Usuario;
import br.com.cruzvita.usuario.service.UsuarioService;

@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    @GetMapping("/teste")
    public String teste(){
        return "API FUNFANDO!!";
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody @Valid UsuarioDTO usuario){
        return service.cadastrar(usuario);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id){
        return service.deletar(id);
    }

    @PutMapping("/alterar")
    public ResponseEntity<String> alterar(@RequestBody Usuario usuario){
        return service.alterar(usuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar(){
        return service.listar();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
