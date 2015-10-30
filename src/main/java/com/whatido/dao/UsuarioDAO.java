package com.whatido.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.whatido.model.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = -6286933047171333L;

	@Inject
	private EntityManager manager;
	
	public List<Usuario> buscarTodos(){
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	public Usuario salvar(Usuario usuario) {
		return manager.merge(usuario);
	}
	
	public Usuario buscarPorId(Integer id){
		return manager.find(Usuario.class, id);
	}
	
	public Usuario buscarPorEmail(String email){
		try{
			return manager.createQuery("from Usuario where upper(email)= :pEmail", Usuario.class)
					.setParameter("pEmail", email.toUpperCase())
					.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
}
