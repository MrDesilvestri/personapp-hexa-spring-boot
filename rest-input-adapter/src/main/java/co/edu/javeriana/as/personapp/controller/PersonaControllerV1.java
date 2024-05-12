package co.edu.javeriana.as.personapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.as.personapp.adapter.PersonaInputAdapterRest;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/persona")
public class PersonaControllerV1 {
	
	@Autowired
	private PersonaInputAdapterRest personaInputAdapterRest;
	
	@ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonaResponse> personas(@PathVariable String database) {
		log.info("Into personas REST API");
			return personaInputAdapterRest.historial(database.toUpperCase());
	}
	
	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse crearPersona(@RequestBody PersonaRequest request) {
		log.info("esta en el metodo crearTarea en el controller del api");
		return personaInputAdapterRest.crearPersona(request);
	}

	@ResponseBody
	@PutMapping(path = "/actualizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse actualizarPersona(@PathVariable int id, @RequestBody PersonaRequest request) {
		log.info("Actualizando la persona con ID: {}", id);
		return personaInputAdapterRest.actualizarPersona(id, request);
	}

	@ResponseBody
	@DeleteMapping(path = "/eliminar/{id}")
	public void eliminarPersona(@PathVariable int id) {
		log.info("Eliminando la persona con ID: {}", id);
		personaInputAdapterRest.eliminarPersona(id);
	}

	@ResponseBody
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonaResponse> getPersonaById(@PathVariable int id) {
		log.info("Obteniendo la persona con ID: {}", id);
		try {
        Person person = personaInputAdapterRest.ObtenerPorID(id);
        if (person != null) {
            return ResponseEntity.ok().body(new PersonaResponse(
                person.getIdentification().toString(),
                person.getFirstName(),
                person.getLastName(),
                Integer.toString(person.getAge()),
                person.getGender().toString(),
                "databaseName", // Esto deberías adaptarlo según necesidades
                "Success"
            ));
				} else {
					return ResponseEntity.notFound().build();
				}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
