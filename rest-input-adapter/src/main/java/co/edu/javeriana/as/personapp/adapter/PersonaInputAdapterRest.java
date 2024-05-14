package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.mapper.PersonaMapperRest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonaInputAdapterRest {

	private final PersonOutputPort personOutputPortMaria;
	private final PersonOutputPort personOutputPortMongo;
	private final PersonaMapperRest personaMapperRest;
    private PersonInputPort personInputPort;

	@Autowired
	public PersonaInputAdapterRest(@Qualifier("personOutputAdapterMaria") PersonOutputPort personOutputPortMaria,
								   @Qualifier("personOutputAdapterMongo") PersonOutputPort personOutputPortMongo,
								   PersonaMapperRest personaMapperRest) {
		this.personOutputPortMaria = personOutputPortMaria;
		this.personOutputPortMongo = personOutputPortMongo;
		this.personaMapperRest = personaMapperRest;
    }

	private void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMaria);
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMongo);
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public List<PersonaResponse> historial(String database) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			setPersonOutputPortInjection(database);
			return personInputPort.findAll().stream()
					.map(database.equalsIgnoreCase(DatabaseOption.MARIA.toString()) ?
							personaMapperRest::fromDomainToAdapterRestMaria :
							personaMapperRest::fromDomainToAdapterRestMongo)
					.collect(Collectors.toList());
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<>();
		}
	}

	public PersonaResponse crearPersona(PersonaRequest request) {
		try {
			setPersonOutputPortInjection(request.getDatabase());
			Person person = personInputPort.create(personaMapperRest.fromAdapterToDomain(request));
			return request.getDatabase().equalsIgnoreCase(DatabaseOption.MARIA.toString()) ?
					personaMapperRest.fromDomainToAdapterRestMaria(person) :
					personaMapperRest.fromDomainToAdapterRestMongo(person);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public PersonaResponse actualizarPersona(Integer cc, PersonaRequest request) {
		try {
			setPersonOutputPortInjection(request.getDatabase());
			Person person = personInputPort.edit(cc, personaMapperRest.fromAdapterToDomain(request));
			return request.getDatabase().equalsIgnoreCase(DatabaseOption.MARIA.toString()) ?
					personaMapperRest.fromDomainToAdapterRestMaria(person) :
					personaMapperRest.fromDomainToAdapterRestMongo(person);
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public Person obtenerPorID(Integer cc, String database) {
		try {
			setPersonOutputPortInjection(database);
			return personInputPort.findById(cc);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public boolean eliminarPersona(Integer cc, String database) {
		try {
			setPersonOutputPortInjection(database);
			return personInputPort.drop(cc);
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
			return false;
		}
	}
}
