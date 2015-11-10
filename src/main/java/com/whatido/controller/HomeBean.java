package com.whatido.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.whatido.security.Seguranca;
import com.whatido.service.NegocioException;

@Named
@RequestScoped
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 7676405586749980993L;

	@Inject
	private Seguranca seguranca;
	
	public void init(){
		if(seguranca.isUsuarioLogado()){
			seguranca.bloquearAcessoDepoisDeLogado();
		}
	}
	
	public void geraErro() {
		throw new NegocioException("teste");
	}

}
