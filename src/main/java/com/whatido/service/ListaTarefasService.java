package com.whatido.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.whatido.dao.ListaTarefasDao;
import com.whatido.dao.TarefasDAO;
import com.whatido.model.ListaTarefas;
import com.whatido.model.Tarefas;
import com.whatido.security.Seguranca;
import com.whatido.util.email.MailConfig;
import com.whatido.util.email.TipoEmail;
import com.whatido.util.freemarker.FreemarkerConfig;
import com.whatido.util.jpa.Transactional;

public class ListaTarefasService implements Serializable {

	private static final long serialVersionUID = -6558996190959395788L;

	@Inject
	ListaTarefasDao listaTarefasDAO;
	@Inject
	Seguranca seguranca;
	@Inject
	private TarefasDAO tarefasDAO;
	
	@Inject
	MailConfig mailConfig;
	@Inject
	FreemarkerConfig freemarkerConfig;
	
	@Transactional
	public ListaTarefas salvar(ListaTarefas lista){
		lista.setUsuario(seguranca.getUsuarioLogado().getUsuario());
		lista = listaTarefasDAO.salvar(lista);
		
		return lista;
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
	
	public ListaTarefas buscarListaParaEdicao(Integer id){
		ListaTarefas lista = listaTarefasDAO.buscarPorId(id);
		if(seguranca.getUsuarioLogado().getUsuario().isAdministrador()){
			return lista;
		}else if(lista.getUsuario().getId() == seguranca.getIdUsuarioLogado()){
			return lista;
		}else{
			throw new NegocioException("Você não tem permissão para acessar esta lista.");
		}
	}
	
	public List<ListaTarefas> buscarListas(){
		if(seguranca.getUsuarioLogado().getUsuario().isAdministrador()){
			return listaTarefasDAO.buscarTodas();
		}else{
			return listaTarefasDAO.buscarTodasPorUsuario(seguranca.getIdUsuarioLogado());
		}
	}
	
	public void enviarEmailConfirmacao(ListaTarefas lista) {
		try {
			HtmlEmail email = (HtmlEmail) mailConfig.getMailConfig();
			email.setSubject("Cadastro Realizado");
			
			String msg = freemarkerConfig.getEmailComTemplate(TipoEmail.NOVALISTA, lista);
			email.setHtmlMsg(msg);
			
			email.addTo(lista.getUsuario().getEmail());
			email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
}
