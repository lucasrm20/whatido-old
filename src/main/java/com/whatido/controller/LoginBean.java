package com.whatido.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whatido.security.Seguranca;
import com.whatido.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 516026819460189696L;
	
	private String email;
	
	@Inject
	private FacesContext facesContext;
	@Inject
	private HttpServletRequest request;
	@Inject
	private HttpServletResponse response;
	@Inject
	private Seguranca seguranca;
	
	public void preRender(){
		if("true".equals(request.getParameter("invalid"))){
			FacesUtil.addErrorMessage("Usuário ou senha inválido.");
		}
		if(seguranca.isUsuarioLogado()){
			seguranca.bloquearAcessoDepoisDeLogado();
		}
	}
	
	public void login() throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
	//getter e setter
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
