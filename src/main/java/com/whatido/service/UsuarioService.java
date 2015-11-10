package com.whatido.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.whatido.dao.UsuarioDAO;
import com.whatido.model.Permissao;
import com.whatido.model.Usuario;
import com.whatido.util.email.MailConfig;
import com.whatido.util.email.TipoEmail;
import com.whatido.util.freemarker.FreemarkerConfig;
import com.whatido.util.jpa.Transactional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 7048550893334496956L;

	@Inject
	UsuarioDAO usuarioDAO;
	@Inject
	MailConfig mailConfig;
	@Inject
	FreemarkerConfig freemarkerConfig;
	
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
			
		}else{
			throw new NegocioException("O email informado não existe no sistema");
		}
	}
	
	public void enviarEmailConfirmacao(Usuario usuario) {
		try {
			HtmlEmail email = (HtmlEmail) mailConfig.getMailConfig();
			email.setSubject("Cadastro Realizado");
			
			String msg = freemarkerConfig.getEmailComTemplate(TipoEmail.CADASTRO, usuario);
			email.setHtmlMsg(msg);
			
			email.addTo(usuario.getEmail());
			email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
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
