package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.mapper.PersonaMapperRest;
import co.edu.javeriana.as.personapp.mariadb.entity.PersonaEntity;
import co.edu.javeriana.as.personapp.mariadb.repository.PersonaRepositoryMaria;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class PersonaInputAdapterRest {

	@Autowired
	@Qualifier("personOutputAdapterMaria")
	private PersonOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("personOutputAdapterMongo")
	private PersonOutputPort personOutputPortMongo;

	@Autowired
	private PersonaMapperRest personaMapperRest;

	@Autowired
	private PersonaRepositoryMaria personaRepositoryMaria;

	PersonInputPort personInputPort;

	private String setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMaria);
			return DatabaseOption.MARIA.toString();
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMongo);
			return  DatabaseOption.MONGO.toString();
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public List<PersonaResponse> historial(String database) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return personInputPort.findAll().stream().map(personaMapperRest::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			}else {
				return personInputPort.findAll().stream().map(personaMapperRest::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}
			
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<PersonaResponse>();
		}
	}

	public PersonaResponse crearPersona(PersonaRequest request) {
		try {
			setPersonOutputPortInjection(request.getDatabase());
			Person person = personInputPort.create(personaMapperRest.fromAdapterToDomain(request));
			return personaMapperRest.fromDomainToAdapterRestMaria(person);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			//return new PersonaResponse("", "", "", "", "", "", "");
		}
		return null;
	}

    public PersonaResponse actualizarPersona(Integer cc, PersonaRequest request) {
        // Buscar la persona existente por su CC
        PersonaEntity persona = personaRepositoryMaria.findById(cc)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada con CC: " + cc));

        // Actualizar los campos de la entidad Persona
        persona.setNombre(request.getFirstName());
        persona.setApellido(request.getLastName());
        persona.setGenero(request.getSex().charAt(0));
        try {
            persona.setEdad(Integer.parseInt(request.getAge()));  // Convertir la edad de String a Integer
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de edad inválido");
        }

        // Guardar los cambios en la base de datos
        persona = personaRepositoryMaria.save(persona);

        // Crear y retornar un objeto PersonaResponse
        PersonaResponse response = new PersonaResponse(
            persona.getCc().toString(),
            persona.getNombre(),
            persona.getApellido(),
            persona.getEdad().toString(),
            persona.getGenero().toString(),
            request.getDatabase(),
            "Updated"
        );

        return response;
    }

	public void eliminarPersona(Integer cc) {
		// Buscar la persona existente por su CC
		PersonaEntity persona = personaRepositoryMaria.findById(cc)
			.orElseThrow(() -> new RuntimeException("Persona no encontrada con CC: " + cc));

		// Eliminar la persona de la base de datos
		personaRepositoryMaria.delete(persona);
	}


}
