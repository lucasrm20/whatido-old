package com.whatido.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.NotBlank;

import com.whatido.model.Usuario;
import com.whatido.security.Seguranca;
import com.whatido.service.UsuarioService;
import com.whatido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class InformacoesBean implements Serializable {

	private static final long serialVersionUID = -7833134750293243574L;
	
	private Usuario usuario;
	private String senhaAtual;
	private String novaSenha;
	
	@Inject
	private Seguranca seguranca;
	
	@Inject
	private UsuarioService usuarioService;
	
	@PostConstruct
	public void init(){
		limpar();
	}
	
	private void limpar(){
		usuario = new Usuario();
		usuario = seguranca.getUsuarioLogado().getUsuario();
		senhaAtual = new String();
		novaSenha = new String();
	}
	
	public void salvarInformacoes(){
		usuario = usuarioService.atualizarInformacoes(usuario);
		FacesUtil.addInfoMessage("Informações alteradas com sucesso.");
	}
	
	public void alterarSenha(){
		usuarioService.alterarSenha(usuario, senhaAtual, novaSenha);
		FacesUtil.addInfoMessage("A senha foi alterada.");
	}
	
	//getters e setters
	public Usuario getUsuario() {
		return usuario;
	}
	
	@NotBlank
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	
	@NotBlank
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}
