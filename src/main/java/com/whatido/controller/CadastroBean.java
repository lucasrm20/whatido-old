package com.whatido.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.whatido.model.Usuario;
import com.whatido.service.UsuarioService;
import com.whatido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroBean implements Serializable {

	private static final long serialVersionUID = 5912037522011035548L;

	private Usuario usuario;
	
	@Inject
	UsuarioService usuarioService;
	
	public CadastroBean() {
		limpar();
	}
	
	private void limpar(){
		usuario = new Usuario();
	}
	
	public void cadastrar(){
		usuario = usuarioService.salvar(usuario);
		limpar();
		FacesUtil.addInfoMessage("Cadastro realizado, por favor verifique seu email.");
	}
	
	//getters e setters
	public Usuario getUsuario() {
		return usuario;
	}
}
