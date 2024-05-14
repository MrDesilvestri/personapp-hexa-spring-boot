package co.edu.javeriana.as.personapp.controller;

import java.util.List;
import javax.validation.Valid;
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
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/persona")
public class PersonaControllerV1 {

	private final PersonaInputAdapterRest personaInputAdapterRest;

	@Autowired
	public PersonaControllerV1(PersonaInputAdapterRest personaInputAdapterRest) {
		this.personaInputAdapterRest = personaInputAdapterRest;
	}

	@ResponseBody
	@GetMapping(path = "/database/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonaResponse> personas(@PathVariable String database) {
		log.info("Into personas REST API");
		return personaInputAdapterRest.historial(database.toUpperCase());
	}

	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonaResponse> crearPersona(@Valid @RequestBody PersonaRequest request) {
		log.info("Creating person in database: {}", request.getDatabase());
		PersonaResponse response = personaInputAdapterRest.crearPersona(request);
		if (response != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@ResponseBody
	@PutMapping(path = "/actualizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonaResponse> actualizarPersona(@PathVariable int id, @Valid @RequestBody PersonaRequest request) {
		log.info("Updating person with ID: {} in database: {}", id, request.getDatabase());
		PersonaResponse response = personaInputAdapterRest.actualizarPersona(id, request);
		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@ResponseBody
	@DeleteMapping(path = "/eliminar/{id}")
	public ResponseEntity<Void> eliminarPersona(@PathVariable int id, @RequestBody PersonaRequest request) {
		log.info("Deleting person with ID: {} in database: {}", id, request.getDatabase());
		boolean deleted = personaInputAdapterRest.eliminarPersona(id, request.getDatabase());
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@ResponseBody
	@GetMapping(path = "/{id}/database/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonaResponse> getPersonaById(@PathVariable int id, @PathVariable String database) {
		log.info("Getting person with ID: {} in database: {}", id, database);
		Person person = personaInputAdapterRest.obtenerPorID(id, database);
		if (person != null) {
			PersonaResponse response = new PersonaResponse(
					person.getIdentification().toString(),
					person.getFirstName(),
					person.getLastName(),
					Integer.toString(person.getAge()),
					person.getGender().toString(),
					database,
					"Success"
			);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
