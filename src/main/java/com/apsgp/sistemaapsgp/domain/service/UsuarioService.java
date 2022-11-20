package com.apsgp.sistemaapsgp.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.apsgp.sistemaapsgp.domain.exception.NegocioException;
import com.apsgp.sistemaapsgp.domain.repository.UsuarioRepository;
import com.apsgp.sistemaapsgp.model.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioService {

	UsuarioRepository usuarioRepository;
	
	
	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(
						() -> new NegocioException("Cliente não encontrado")
							);
	}
	
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		boolean emailJaExistente = usuarioRepository.findByEmail(usuario.getEmail())
													.stream()
													.anyMatch(usuarioExistente -> !usuarioExistente.equals(usuario));
		if(emailJaExistente == true) {
			throw new NegocioException("Email já existente");
		} else {
			return usuarioRepository.save(usuario);
		}
	}

	
	@Transactional
	public void excluir(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}
	
	
	
	
	public Usuario loginUsuario (String email, String senha) throws NegocioException {
		Usuario userLogin = usuarioRepository.buscarLogin(email, senha);
		return userLogin;
	}
	
	
}


