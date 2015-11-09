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
}
