package com.whatido.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.whatido.model.ListaTarefas;

@Named
@ViewScoped
public class TarefaBean implements Serializable {

	private static final long serialVersionUID = -3789145756763727898L;
	
	private ListaTarefas listaPai;
	
	//getters setters
	public ListaTarefas getListaPai() {
		return listaPai;
	}
	public void setListaPai(ListaTarefas listaPai) {
		this.listaPai = listaPai;
	}
	
}
