package com.whatido.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.whatido.model.ListaTarefas;
import com.whatido.model.Tarefas;
import com.whatido.service.ListaTarefasService;
import com.whatido.service.TarefasService;
import com.whatido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TarefaBean implements Serializable {

	private static final long serialVersionUID = -3789145756763727898L;
	
	private ListaTarefas listaPai;
	
	private Tarefas tarefa;
	
	private Tarefas itemSelecionado;
	
	private Tarefas tarefaSorteada;

	@Inject
	private ListaTarefasService listaTarefasService;
	@Inject
	private TarefasService tarefasService;
	
	@PostConstruct
	private void init(){
		limpar();
	}
	
	private void limpar(){
		tarefa = new Tarefas();
	}
	
	public void alterarDescricaoLista(){
		listaPai = listaTarefasService.salvarEdicao(listaPai);
		FacesUtil.addInfoMessage("Descrição da lista atualizada. " + listaPai.getDescricao());
	}
	
	public void adicionarTarefa(){
		tarefa.setLista(listaPai);
		tarefa = tarefasService.salvar(tarefa);
		listaPai.getTarefas().add(tarefa);
		FacesUtil.addInfoMessage("Tarefa adicionada. " + tarefa.getDescricao());
		limpar();
	}
	
	public void excluir(){
		listaTarefasService.deletarUmaTarefa(listaPai, itemSelecionado);
		listaPai.getTarefas().remove(itemSelecionado);
		FacesUtil.addInfoMessage("Tarefa excluída com sucesso.");
	}
	
	public void sortear(){
		tarefaSorteada = tarefasService.sortear(listaPai);
		FacesUtil.addInfoMessage("Sorteamos a tarefa: " + tarefaSorteada.getDescricao());
	}
	
	//getters setters
	public ListaTarefas getListaPai() {
		return listaPai;
	}
	public void setListaPai(ListaTarefas listaPai) {
		this.listaPai = listaPai;
	}
	public Tarefas getTarefa() {
		return tarefa;
	}
	public Tarefas getItemSelecionado() {
		return itemSelecionado;
	}
	public void setItemSelecionado(Tarefas itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}
	public Tarefas getTarefaSorteada() {
		return tarefaSorteada;
	}
	
}
