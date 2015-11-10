package com.whatido.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.whatido.service.UsuarioService;
import com.whatido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RecuperarSenhaBean implements Serializable {

	private static final long serialVersionUID = -3804894800165307562L;
	
	private String email;
	
	@Inject
	private UsuarioService usuarioService;
	
	public void recuperar(){
		usuarioService.recuperarSenha(email);
		FacesUtil.addInfoMessage("Seus dados de acesso foram enviados para seu email.");
	}
	
	//getters e setters
	@NotBlank @Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
