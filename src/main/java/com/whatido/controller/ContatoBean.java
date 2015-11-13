package com.whatido.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.whatido.util.email.EmailContato;
import com.whatido.util.email.EnviadorDeEmail;
import com.whatido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ContatoBean implements Serializable {
	
	private static final long serialVersionUID = -2467745095464233109L;
	
	private EmailContato emailContato;
	
	@Inject
	private EnviadorDeEmail enviadorEmail;
	
	@PostConstruct
	public void init(){
		limpar();
	}
	
	public void limpar(){
		emailContato = new EmailContato();
	}
	
	public void enviar(){
		enviadorEmail.enviarEmailContato(emailContato);
		limpar();
		FacesUtil.addInfoMessage("Mensagem Enviada.");
	}
	
	//getters e setters
	public EmailContato getEmailContato() {
		return emailContato;
	}
	
}
