package com.whatido.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = -4597565051208872818L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Permissao pemissao = Permissao.COMUM;
	
	@OneToMany(mappedBy="tarefas")
	private List<ListaTarefas> listaTarefas = new ArrayList<>();
	
	//metodos
	@Transient
	public boolean isAdministrador(){
		return Permissao.ADMINISTRADOR.equals(this.pemissao);
	}

	//getters e setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Permissao getPemissao() {
		return pemissao;
	}

	public void setPemissao(Permissao pemissao) {
		this.pemissao = pemissao;
	}
	
	public List<ListaTarefas> getListaTarefas() {
		return listaTarefas;
	}
	
	public void setListaTarefas(List<ListaTarefas> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	//hash e equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
