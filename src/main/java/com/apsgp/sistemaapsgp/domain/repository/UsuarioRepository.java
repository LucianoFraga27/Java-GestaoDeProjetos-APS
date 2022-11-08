package com.apsgp.sistemaapsgp.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apsgp.sistemaapsgp.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
	List<Usuario> findByNome(String nome);
	List<Usuario> findByNomeContaining(String Nome);
	Optional<Usuario> findByEmail(String email);
}
