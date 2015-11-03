package com.whatido.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.whatido.dao.ListaTarefasDao;
import com.whatido.dao.TarefasDAO;
import com.whatido.model.ListaTarefas;
import com.whatido.model.Tarefas;
import com.whatido.security.Seguranca;
import com.whatido.util.jpa.Transactional;

public class ListaTarefasService implements Serializable {

	private static final long serialVersionUID = -6558996190959395788L;

	@Inject
	ListaTarefasDao listaTarefasDAO;
	@Inject
	Seguranca seguranca;
	@Inject
	private TarefasDAO tarefasDAO;
	
	@Transactional
	public ListaTarefas salvar(ListaTarefas lista){
		lista.setUsuario(seguranca.getUsuarioLogado().getUsuario());
		return listaTarefasDAO.salvar(lista);
	}
	
	@Transactional
	public ListaTarefas salvarEdicao(ListaTarefas lista){
		return listaTarefasDAO.salvar(lista);
	}
	
	@Transactional
	public void deletarUmaTarefa(ListaTarefas lista, Tarefas tarefa){
		if(lista.getTarefas().contains(tarefa)){
			lista.getTarefas().remove(tarefa);
		}
		listaTarefasDAO.salvar(lista);
		tarefasDAO.excluir(tarefa);
	}
	
}
