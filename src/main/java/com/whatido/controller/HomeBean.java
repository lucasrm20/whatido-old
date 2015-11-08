package com.whatido.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.whatido.model.Usuario;
import com.whatido.service.NegocioException;
import com.whatido.util.email.MailConfig;
import com.whatido.util.freemarker.FreemarkerConfig;

@Named
@ViewScoped
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 7676405586749980993L;

	@Inject
	MailConfig mailConfig;
	@Inject
	FreemarkerConfig freemarkerConfig;

	public void geraErro() {
		throw new NegocioException("teste");
	}

	public void testarEmail() {
		try {
			HtmlEmail email = (HtmlEmail) mailConfig.getMailConfig();
			email.setSubject("Teste");
			
			Usuario teste = new Usuario();
			teste.setNome("Lucas");
			
			String msg = freemarkerConfig.getEmailComTemplate("pedido.ftl", teste);
			email.setHtmlMsg(msg);
			
			email.addTo("lrpg_doidao@hotmail.com");
			email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
