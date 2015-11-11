package com.whatido.util.email;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.whatido.model.ListaTarefas;
import com.whatido.model.Usuario;
import com.whatido.util.freemarker.FreemarkerConfig;

public class EnviadorDeEmail implements Serializable {

	private static final long serialVersionUID = 4817387119587823095L;
	
	@Inject
	MailConfig mailConfig;
	@Inject
	FreemarkerConfig freemarkerConfig;
	
	public void enviarEmailConfirmacao(Object dados, String assunto, TipoEmail tipoEmail) {
		try {
			HtmlEmail email = (HtmlEmail) mailConfig.getMailConfig();
			email.setSubject(assunto);
			
			String msg = freemarkerConfig.getEmailComTemplate(tipoEmail, dados);
			email.setHtmlMsg(msg);
			
			if(dados instanceof ListaTarefas){
				ListaTarefas lista = (ListaTarefas) dados;
				email.addTo(lista.getUsuario().getEmail());
			}else if(dados instanceof Usuario){
				Usuario usuario = (Usuario) dados;
				email.addTo(usuario.getEmail());
			}
			
			email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
