package co.edu.javeriana.as.personapp.mapper;

import org.springframework.stereotype.Component;

import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;

@Component
public class PersonaMapperRest {

	public Person fromAdapterToDomain(PersonaRequest request) {
		Person person = new Person();
		person.setIdentification(Integer.parseInt(request.getDni()));
		person.setFirstName(request.getFirstName());
		person.setLastName(request.getLastName());
		person.setGender(validateGender(request.getGender()));
		person.setAge(Integer.parseInt(request.getAge()));
		return person;
	}

	public PersonaResponse fromDomainToAdapterRestMaria(Person person) {
		return new PersonaResponse(
				person.getIdentification().toString(),
				person.getFirstName(),
				person.getLastName(),
				person.getAge().toString(),
				person.getGender().toString(),
				"MariaDB",
				"Success"
		);
	}

	public PersonaResponse fromDomainToAdapterRestMongo(Person person) {
		return new PersonaResponse(
				person.getIdentification().toString(),
				person.getFirstName(),
				person.getLastName(),
				person.getAge().toString(),
				person.getGender().toString(),
				"MongoDB",
				"Success"
		);
	}

	private Gender validateGender(String gender) {
		switch (gender.toUpperCase()) {
			case "F":
				return Gender.FEMALE;
			case "M":
				return Gender.MALE;
			default:
				return Gender.OTHER;
		}
	}
}
