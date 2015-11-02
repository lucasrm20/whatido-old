package com.whatido.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.whatido.dao.ListaTarefasDao;
import com.whatido.model.ListaTarefas;
import com.whatido.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=ListaTarefas.class)
public class ListaTarefasConverter implements Converter {

	ListaTarefasDao listaDAO;
	
	public ListaTarefasConverter() {
		listaDAO = CDIServiceLocator.getBean(ListaTarefasDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ListaTarefas retorno = null;
		
		if(value != null){
			Integer id = Integer.parseInt(value);
			retorno = listaDAO.buscarPorId(id);
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
