package com.whatido.util.email;

public enum TipoEmail {
	
	CADASTRO ("cadastro.ftl"), NOVALISTA ("novalista.ftl");
	
	private String descricao;

	private TipoEmail(String descricao) {
		this.descricao = descricao;	
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
