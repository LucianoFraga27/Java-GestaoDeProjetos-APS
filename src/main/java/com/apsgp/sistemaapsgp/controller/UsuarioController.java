package com.apsgp.sistemaapsgp.controller;


import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private UsuarioService usuarioService;
	
	@GetMapping("/listar")
	@ResponseStatus(HttpStatus.OK)
	public java.util.List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();	
	}
	
	
	@GetMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Usuario> encontraUsuario(@PathVariable Long usuarioId){
		return usuarioRepository.findById(usuarioId)
								.map(u -> ResponseEntity.ok(u))
								.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable Long usuarioId, @RequestBody Usuario usuario){
		if(!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		usuario.setId(usuarioId);
		usuario = usuarioService.salvar(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> remover(@PathVariable Long usuarioId){
		if(!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.excluir(usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	

	@GetMapping("/helpAPI")
	public String helpAPI() {
		String str = "Funcionalidades da API"
				+ "\n-----------------------------"
				+ "\nNome: listar Usuarios,"
				+ "\nVerbo: GET,"
				+ "\nendereço: localhost:8080/usuario/listar,"
				+ "\nexemplo: [];"
				+"\n-----------------------------"
				+ "\nNome: encontra Usuario,"
				+ "\nVerbo: GET,"
				+ "\nendereço: localhost:8080/usuario/{id},"
				+ "\nexemplo: [1,2,3,4,5];"
				+"\n-----------------------------"
				+ "\nNome: cadastrar Usuarios,"
				+ "\nVerbo: POST,"
				+ "\nendereço: localhost:8080/usuario/cadastrar,"
				+ "\nexemplo: ["
				+ "\n	{"
				+ "\n	'nome': 'luciano',"
				+ "\n	'email': 'lucianinho@email.com',"
				+ "\n	'senha': '123'"
				+ "\n	}"
				+ "\n];"
				+ "\nOBS: JSON no corpo da mensagem."
				+ "\n-----------------------------"
				+ "\nNome: editar Usuarios,"
				+ "\nVerbo: PUT,"
				+ "\nendereço: localhost:8080/usuario/{id},"
				+ "\nexemplo: ["
				+ "\n	{"
				+ "\n	'nome': 'luciano',"
				+ "\n	'email': 'lucianinhoEditado@email.com',"
				+ "\n	'senha': '123'"
				+ "\n	}"
				+ "\n];"
				+ "\nOBS: JSON no corpo da mensagem."
				+ "\n-----------------------------"
				+ "\nNome: remover Usuarios,"
				+ "\nVerbo: DELETE,"
				+ "\nendereço: localhost:8080/usuario/{id},"
				+ "\nexemplo: [1,2,3,4,5];"
				+ "";
		return str;
	}
	
	
	
}
