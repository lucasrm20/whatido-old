package com.whatido.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.whatido.model.ListaTarefas;
import com.whatido.service.ListaTarefasService;
import com.whatido.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=ListaTarefas.class)
public class ListaTarefasConverter implements Converter {

	ListaTarefasService listaService;
	
	public ListaTarefasConverter() {
		listaService = CDIServiceLocator.getBean(ListaTarefasService.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ListaTarefas retorno = null;
		
		if(value != null){
			Integer id = Integer.parseInt(value);
			retorno = listaService.buscarListaParaEdicao(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((ListaTarefas) value).getId().toString();
		}
		
		return "";
	}

}
