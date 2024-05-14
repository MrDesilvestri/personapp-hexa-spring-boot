package co.edu.javeriana.as.personapp.mongo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mongo.document.EstudiosDocument;
import co.edu.javeriana.as.personapp.mongo.document.PersonaDocument;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import lombok.NonNull;

@Mapper
public class PersonaMapperMongo {

	private final EstudiosMapperMongo estudiosMapperMongo;

	private final TelefonoMapperMongo telefonoMapperMongo;

	@Autowired
	public PersonaMapperMongo(@Lazy EstudiosMapperMongo estudiosMapperMongo, @Lazy TelefonoMapperMongo telefonoMapperMongo) {
		this.estudiosMapperMongo = estudiosMapperMongo;
		this.telefonoMapperMongo = telefonoMapperMongo;
	}

	public PersonaDocument fromDomainToAdapter(Person person) {
		PersonaDocument personaDocument = new PersonaDocument();
		personaDocument.setId(person.getIdentification());
		personaDocument.setNombre(person.getFirstName());
		personaDocument.setApellido(person.getLastName());
		personaDocument.setGenero(validateGenero(person.getGender()));
		personaDocument.setEdad(validateEdad(person.getAge()));
		personaDocument.setEstudios(validateEstudios(person.getStudies()));
		personaDocument.setTelefonos(validateTelefonos(person.getPhoneNumbers()));
		return personaDocument;
	}

	private String validateGenero(@NonNull Gender gender) {
		return gender == Gender.FEMALE ? "F" : gender == Gender.MALE ? "M" : " ";
	}

	private Integer validateEdad(Integer age) {
		return age != null && age >= 0 ? age : null;
	}

	private List<EstudiosDocument> validateEstudios(List<Study> studies) {
		return studies != null && !studies.isEmpty() ? studies.stream()
				.map(estudiosMapperMongo::fromDomainToAdapter).collect(Collectors.toList())
				: new ArrayList<>();
	}

	private List<TelefonoDocument> validateTelefonos(List<Phone> phoneNumbers) {
		return phoneNumbers != null && !phoneNumbers.isEmpty() ? phoneNumbers.stream()
				.map(telefonoMapperMongo::fromDomainToAdapter).collect(Collectors.toList())
				: new ArrayList<>();
	}

	public Person fromAdapterToDomain(PersonaDocument personaDocument) {
		Person person = new Person();
		person.setIdentification(personaDocument.getId());
		person.setFirstName(personaDocument.getNombre());
		person.setLastName(personaDocument.getApellido());
		person.setGender(validateGender(personaDocument.getGenero()));
		person.setAge(validateAge(personaDocument.getEdad()));
		person.setStudies(validateStudies(personaDocument.getEstudios()));
		person.setPhoneNumbers(validatePhones(personaDocument.getTelefonos()));
		return person;
	}

	private @NonNull Gender validateGender(String genero) {
		return "F".equals(genero) ? Gender.FEMALE : "M".equals(genero) ? Gender.MALE : Gender.OTHER;
	}

	private Integer validateAge(Integer edad) {
		return edad != null && edad >= 0 ? edad : null;
	}

	private List<Study> validateStudies(List<EstudiosDocument> estudiosDocuments) {
		return estudiosDocuments != null && !estudiosDocuments.isEmpty() ? estudiosDocuments.stream()
				.map(estudiosMapperMongo::fromAdapterToDomain).collect(Collectors.toList())
				: new ArrayList<>();
	}

	private List<Phone> validatePhones(List<TelefonoDocument> telefonosDocuments) {
		return telefonosDocuments != null && !telefonosDocuments.isEmpty() ? telefonosDocuments.stream()
				.map(telefonoMapperMongo::fromAdapterToDomain).collect(Collectors.toList())
				: new ArrayList<>();
	}
}
