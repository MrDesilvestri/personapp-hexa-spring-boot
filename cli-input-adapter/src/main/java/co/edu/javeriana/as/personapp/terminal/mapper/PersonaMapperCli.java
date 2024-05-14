package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.mariadb.entity.PersonaEntity;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;

@Mapper
public class PersonaMapperCli {

	public PersonaModelCli fromDomainToAdapterCli(Person person) {
		PersonaModelCli personaModelCli = new PersonaModelCli();
		personaModelCli.setCc(person.getIdentification());
		personaModelCli.setNombre(person.getFirstName());
		personaModelCli.setApellido(person.getLastName());
		personaModelCli.setGenero(person.getGender().toString());
		personaModelCli.setEdad(person.getAge());
		return personaModelCli;
	}

	public Person fromAdapterCliToDomain(PersonaModelCli personaModelCli) {
		Person person = new Person();
		if (personaModelCli.getNombre() == null) {
			throw new IllegalArgumentException("Nombre no puede ser nulo");
		}
		person.setIdentification(personaModelCli.getCc());
		person.setFirstName(personaModelCli.getNombre());
		person.setLastName(personaModelCli.getApellido());
		person.setGender(person.mapeoGenero(personaModelCli.getGenero()));
		person.setAge(personaModelCli.getEdad());
		return person;
	}


	public PersonaEntity fromDomainToEntity(Person person) {
        if (person == null) {
            return null;
        }
        PersonaEntity entity = new PersonaEntity();
        entity.setCc(person.getIdentification());
        entity.setNombre(person.getFirstName());
        entity.setApellido(person.getLastName());
        entity.setGenero(convertGenderToChar(person.getGender()));
        entity.setEdad(person.getAge());

        return entity;
	}

    private Character convertGenderToChar(Gender gender) {
        if (gender == null) return 'U';  // 'U' para desconocido
        switch (gender) {
            case MALE:
                return 'M';
            case FEMALE:
                return 'F';
            case OTHER:
                return 'O';
            default:
                return 'U';  // Considerar un valor por defecto
        }
    }

}
