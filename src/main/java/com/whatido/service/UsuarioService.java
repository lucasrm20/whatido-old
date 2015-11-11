package com.whatido.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.whatido.dao.UsuarioDAO;
import com.whatido.model.Permissao;
import com.whatido.model.Usuario;
import com.whatido.util.email.EnviadorDeEmail;
import com.whatido.util.email.TipoEmail;
import com.whatido.util.jpa.Transactional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 7048550893334496956L;

	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private EnviadorDeEmail enviadorEmail;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		if(isEmailJaCadastrado(usuario)){
			throw new NegocioException("O email '"+ usuario.getEmail() +"' já esta cadastrado");
		}else{			
			usuario.setPemissao(Permissao.COMUM);
			usuario = usuarioDAO.salvar(usuario);
			
			return usuario;
		}
	}
	
	@Transactional
	public Usuario atualizarInformacoes(Usuario usuario){
		usuario = usuarioDAO.salvar(usuario);
		return usuario;
	}
	
	@Transactional
	public Usuario alterarSenha(Usuario usuario, String senhaAtual, String novaSenha){
		if(compararSenhas(usuario.getSenha(), senhaAtual)){
			usuario.setSenha(novaSenha);
			usuario = usuarioDAO.salvar(usuario);
		}else{
			throw new NegocioException("A Senha atual informada é incorreta");
		}
		
		return usuario;
	}
	
	public void recuperarSenha(String email) {
		Usuario usuario = usuarioDAO.buscarPorEmail(email);
		if(usuario != null){
			enviadorEmail.enviarEmailConfirmacao(usuario, "Sua senha foi recuperada", TipoEmail.RECUPERARSENHA);
		}else{
			throw new NegocioException("O email informado não existe no sistema");
		}
	}

	public boolean isEmailJaCadastrado(Usuario usuario){
		if(usuarioDAO.buscarPorEmail(usuario.getEmail()) != null){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean compararSenhas(String senhaAtual, String senhaAtualInformada ){
		return senhaAtual.equals(senhaAtualInformada);
	}

}
