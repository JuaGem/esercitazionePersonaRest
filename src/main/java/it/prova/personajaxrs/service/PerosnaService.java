package it.prova.personajaxrs.service;

import java.util.List;

import it.prova.personajaxrs.dao.PersonaDAO;
import it.prova.personajaxrs.model.Persona;

public interface PerosnaService {
	public List<Persona> listAllElements() throws Exception;

	public Persona caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Persona personaInstance) throws Exception;

	public void inserisciNuovo(Persona personaInstance) throws Exception;

	public void rimuovi(Persona personaInstance) throws Exception;
	
	public List<Persona> findByExample (Persona persona) throws Exception;
	// per injection
	public void setPerosnaDAO(PersonaDAO personaDAO);
}
