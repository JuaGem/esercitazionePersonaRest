package it.prova.personajaxrs.web.rest.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.prova.personajaxrs.model.Persona;
import it.prova.personajaxrs.service.MyServiceFactory;
import it.prova.personajaxrs.service.PerosnaService;

@Path("/persona")
public class PersonaResource {
	private static final Logger LOGGER = Logger.getLogger(PersonaResource.class.getName());

	@Context
	HttpServletRequest request;

	private PerosnaService personaService;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	public PersonaResource() {
		personaService = MyServiceFactory.getPersonaServiceInstance();
	}
	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersona(@PathParam("id") Long id) throws JsonProcessingException {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona personaDaCaricare = null;
		try {
			personaDaCaricare = personaService.caricaSingoloElemento(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(convertDate(personaDaCaricare)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertiNuovapersona(Persona personaInput) throws JsonProcessingException {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			personaService.inserisciNuovo(personaInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(convertDate(personaInput)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() throws JsonProcessingException {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Persona> result = null;
		try {
			result = MyServiceFactory.getPersonaServiceInstance().listAllElements();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(convertDateList(result)).build();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPersona(Persona personaInput) throws JsonProcessingException {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona example = new Persona();
		example.setNome(personaInput.getNome());
		example.setCognome(personaInput.getCognome());
		example.setDataNascita(personaInput.getDataNascita());
		List<Persona> result = null;
		try {
			result = MyServiceFactory.getPersonaServiceInstance().findByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(convertDateList(result)).build();
	}

	@DELETE
	@Path("{id : \\d+}")
	public Response deletepersona(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			Persona personaDaEliminare = MyServiceFactory.getPersonaServiceInstance().caricaSingoloElemento(id);
			MyServiceFactory.getPersonaServiceInstance().rimuovi(personaDaEliminare);
			return Response.status(200).entity("Rimossa persona con id: "+id).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
		}


	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response aggiornapersona(Persona personaInput) throws JsonProcessingException {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			MyServiceFactory.getPersonaServiceInstance().aggiorna(personaInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(convertDate(personaInput)).build();
	}
	
	private String convertDate(Persona persona) throws JsonProcessingException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = objectMapper.writeValueAsString(persona);
        return json;
    }

    private String convertDateList(List<Persona> persone) throws JsonProcessingException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = objectMapper.writeValueAsString(persone);
        return json;
    }
	
}
