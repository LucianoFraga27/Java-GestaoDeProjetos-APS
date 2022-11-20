package com.apsgp.sistemaapsgp.controller;


import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.apsgp.sistemaapsgp.domain.exception.NegocioException;
import com.apsgp.sistemaapsgp.domain.repository.UsuarioRepository;
import com.apsgp.sistemaapsgp.domain.service.UsuarioService;
import com.apsgp.sistemaapsgp.model.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UsuarioController {

	
	private UsuarioRepository usuarioRepository;
	private UsuarioService usuarioService;
	
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@GetMapping("/usuarios")
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
	
	
	/*Login
	 * */
	
	@PostMapping("/login")
	public ModelAndView login (@Valid @RequestBody Usuario usuario, BindingResult br, HttpSession session ) throws NoSuchAlgorithmException, NegocioException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("Usuario",new Usuario());
		if(br.hasErrors()) {
			mv.setViewName("Login/login");
		}
		Usuario usuarioLogin =  usuarioService.loginUsuario(usuario.getEmail(), usuario.getSenha());
		if(usuarioLogin == null) {
			mv.addObject("msg","usuario nao encontrado.");
		} else {
			session.setAttribute("usuarioLogado", usuarioLogin);
			return index();
		}
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
