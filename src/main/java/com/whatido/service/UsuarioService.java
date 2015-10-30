package com.whatido.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.whatido.dao.UsuarioDAO;
import com.whatido.model.Permissao;
import com.whatido.model.Usuario;
import com.whatido.util.jpa.Transactional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 7048550893334496956L;

	@Inject
	UsuarioDAO usuarioDAO;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		if(isEmailJaCadastrado(usuario)){
			throw new NegocioException("O email '"+ usuario.getEmail() +"' já esta cadastrado");
		}else{			
			usuario.setPemissao(Permissao.COMUM);
			return usuarioDAO.salvar(usuario);
		}
	}
	
	public boolean isEmailJaCadastrado(Usuario usuario){
		if(usuarioDAO.buscarPorEmail(usuario.getEmail()) != null){
			return true;
		}else{
			return false;
		}
	}
}
