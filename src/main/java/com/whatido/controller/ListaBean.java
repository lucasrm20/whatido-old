package com.whatido.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.whatido.dao.ListaTarefasDao;
import com.whatido.model.ListaTarefas;
import com.whatido.service.ListaTarefasService;
import com.whatido.util.email.EnviadorDeEmail;
import com.whatido.util.email.TipoEmail;
import com.whatido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ListaBean implements Serializable {

	private static final long serialVersionUID = 7749169970369902742L;
	
	private ListaTarefas novaLista;
	private List<ListaTarefas> listaDeListas;
	
	@Inject
	ListaTarefasService listaService;
	@Inject
	ListaTarefasDao listaDAO;
	
	@Inject
	private EnviadorDeEmail enviadorEmail;
	
	private ListaTarefas itemSelecionado;
	
	@PostConstruct
	public void init(){
		limpar();
	}
	
	private void limpar(){
		novaLista = new ListaTarefas();
		listaDeListas = listaService.buscarListas();
	}
	
	public void salvar(){
		novaLista = listaService.salvar(novaLista);
		enviadorEmail.enviarEmailConfirmacao(novaLista, "Sua lista foi criada", TipoEmail.NOVALISTA);
		limpar();
		FacesUtil.addInfoMessage("Lista cadastrada.");
	}
	
	public void excluir(){
		listaDAO.deletar(itemSelecionado);
		listaDeListas.remove(itemSelecionado);
		FacesUtil.addInfoMessage(itemSelecionado.getDescricao() + " foi excluído.");
	}
	
	//getters e setters
	public ListaTarefas getNovaLista() {
		return novaLista;
	}
	public List<ListaTarefas> getListaDeListas() {
		return listaDeListas;
	}
	public ListaTarefas getItemSelecionado() {
		return itemSelecionado;
	}
	public void setItemSelecionado(ListaTarefas itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

}
