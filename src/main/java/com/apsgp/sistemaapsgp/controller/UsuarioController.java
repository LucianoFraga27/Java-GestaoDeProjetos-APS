package com.apsgp.sistemaapsgp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apsgp.sistemaapsgp.domain.repository.UsuarioRepository;
import com.apsgp.sistemaapsgp.model.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listar")
	public java.util.List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
	
}
