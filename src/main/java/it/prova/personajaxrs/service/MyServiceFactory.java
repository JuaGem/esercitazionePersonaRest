package it.prova.personajaxrs.service;

import it.prova.personajaxrs.dao.PersonaDAO;
import it.prova.personajaxrs.dao.PersonaDAOImpl;

public class MyServiceFactory {

	private static PerosnaService PERSONA_SERVICE_INSTANCE;
	private static PersonaDAO PERSONA_DAO_INSTANCE;

	public static PerosnaService getPersonaServiceInstance() {
		if (PERSONA_SERVICE_INSTANCE == null)
			PERSONA_SERVICE_INSTANCE = new PersonaServiceImpl();

		if (PERSONA_DAO_INSTANCE == null)
			PERSONA_DAO_INSTANCE = new PersonaDAOImpl();

		PERSONA_SERVICE_INSTANCE.setPerosnaDAO(PERSONA_DAO_INSTANCE);
		return PERSONA_SERVICE_INSTANCE;
	}

}
