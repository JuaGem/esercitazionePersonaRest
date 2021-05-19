package it.prova.personajaxrs.dao;

import java.util.List;

import it.prova.personajaxrs.model.Persona;

public interface PersonaDAO extends IBaseDAO<Persona>{
	
	public List<Persona> findByExample(Persona example) throws Exception;
	
}
