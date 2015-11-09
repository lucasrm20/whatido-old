package com.whatido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.whatido.model.ListaTarefas;
import com.whatido.service.NegocioException;
import com.whatido.util.jpa.Transactional;

public class ListaTarefasDao implements Serializable {

	private static final long serialVersionUID = 1269264775102075842L;
	
	@Inject
	private EntityManager manager;

	public ListaTarefas salvar(ListaTarefas lista) {
		return manager.merge(lista);
	}
	
	@Transactional
	public void deletar(ListaTarefas lista){
		try{
			lista = buscarPorId(lista.getId());
			manager.remove(lista);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("A lista não pode ser removida.");
		}
	}
	
	public List<ListaTarefas> buscarTodas(){
		List<ListaTarefas> resultado = new ArrayList<>();
		resultado = manager.createQuery("from ListaTarefas", ListaTarefas.class).getResultList();
		
		return resultado;
	}
	
	public List<ListaTarefas> buscarTodasPorUsuario(Integer id){
		return manager.createQuery("from ListaTarefas where usuario.id= :pId", ListaTarefas.class)
				.setParameter("pId", id)
				.getResultList();
	}
	
	public ListaTarefas buscarPorId(Integer id){
		return manager.find(ListaTarefas.class, id);
	}

}
