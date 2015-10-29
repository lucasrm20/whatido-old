package com.whatido.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.whatido.service.NegocioException;

@Named
@ViewScoped
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 7676405586749980993L;
	
	public void geraErro(){
		throw new NegocioException("teste");
	}
	
}
