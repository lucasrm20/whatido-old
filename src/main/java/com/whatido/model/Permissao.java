package com.whatido.model;

public enum Permissao {
	COMUM ("Comum"),
	ADMINISTRADOR ("Administrador");
	
	private String descricao;

	private Permissao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
