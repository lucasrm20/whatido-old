package com.whatido.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.whatido.model.Tarefas;
import com.whatido.service.NegocioException;

public class TarefasDAO implements Serializable {

	private static final long serialVersionUID = -8942008389727554251L;
	
	@Inject
	private EntityManager manager;
	
	public Tarefas salvar(Tarefas tarefa){
		return manager.merge(tarefa);
	}
	
	public void excluir(Tarefas tarefa){
		try{
			tarefa = buscarPorId(tarefa.getId());
			manager.remove(tarefa);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("A tarefa não pode ser removida");
		}
	}

	private Tarefas buscarPorId(Integer id) {
		return manager.find(Tarefas.class, id);
	}

}
