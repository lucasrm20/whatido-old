package com.whatido.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.whatido.model.Usuario;
import com.whatido.security.Seguranca;
import com.whatido.service.UsuarioService;
import com.whatido.util.email.EnviadorDeEmail;
import com.whatido.util.email.TipoEmail;
import com.whatido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroBean implements Serializable {

	private static final long serialVersionUID = 5912037522011035548L;

	private Usuario usuario;
	
	@Inject
	UsuarioService usuarioService;
	
	@Inject
	private Seguranca seguranca;
	
	@Inject
	private EnviadorDeEmail enviadorEmail;
	
	public CadastroBean() {
		limpar();
	}
	
	public void init(){
		if(seguranca.isUsuarioLogado()){
			seguranca.bloquearAcessoDepoisDeLogado();
		}
	}
	
	private void limpar(){
		usuario = new Usuario();
	}
	
	public void cadastrar(){
		usuario = usuarioService.salvar(usuario);
		enviadorEmail.enviarEmailConfirmacao(usuario, "Cadastro Realizado", TipoEmail.CADASTRO);
		limpar();
		FacesUtil.addInfoMessage("Cadastro realizado, por favor verifique seu email.");
	}
	
	//getters e setters
	public Usuario getUsuario() {
		return usuario;
	}
}
