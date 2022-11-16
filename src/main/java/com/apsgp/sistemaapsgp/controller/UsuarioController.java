package com.apsgp.sistemaapsgp.controller;


import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apsgp.sistemaapsgp.domain.repository.UsuarioRepository;
import com.apsgp.sistemaapsgp.domain.service.UsuarioService;
import com.apsgp.sistemaapsgp.model.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	
	private UsuarioRepository usuarioRepository;
	//private UsuarioService usuarioService;
	
	@GetMapping("/listar")
	public java.util.List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();	
	}
	

	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> encontraUsuario(@PathVariable Long usuarioId){
		return usuarioRepository.findById(usuarioId)
								.map(u -> ResponseEntity.ok(u))
								.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
		
		return usuarioRepository.save(usuario);
		//return usuarioService.salvar(usuario);
	}

}
