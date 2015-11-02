package com.whatido.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {
	
	public String getNomeUsuario(){
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if(usuarioLogado != null){
			nome = usuarioLogado.getUsuario().getNome();
		}
		
		return nome;
	}

	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if(auth != null && auth.getPrincipal() != null){
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	
	@RequestScoped
	public boolean isUsuarioLogado(){
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		return auth != null && auth.getPrincipal() != null;
	}
	
}
