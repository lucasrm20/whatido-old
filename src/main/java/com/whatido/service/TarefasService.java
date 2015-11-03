package com.whatido.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.whatido.dao.TarefasDAO;
import com.whatido.model.ListaTarefas;
import com.whatido.model.Tarefas;
import com.whatido.util.jpa.Transactional;

public class TarefasService implements Serializable {

	private static final long serialVersionUID = -8771279963311546731L;

	@Inject
	private TarefasDAO tarefasDAO;
	
	@Transactional
	public Tarefas salvar(Tarefas tarefa){
		return tarefasDAO.salvar(tarefa);
	}

	public Tarefas sortear(ListaTarefas listaPai) {
		List<Tarefas> lista = new ArrayList<>(listaPai.getTarefas());
		
		for (Iterator<Tarefas> i = lista.iterator(); i.hasNext();) {
			Tarefas item = i.next();
			
			if(item.getConcluido().equals(Boolean.TRUE)){
				i.remove();
			}
		}
		
		if(!lista.isEmpty()){			
			Collections.shuffle(lista);
		}else{
			throw new NegocioException("Todos os itens desta lista já foram concluídos.");
		}
		
		return lista.get(0);
	}
	
}
