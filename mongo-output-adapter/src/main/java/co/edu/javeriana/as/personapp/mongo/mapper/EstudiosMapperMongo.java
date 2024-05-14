package co.edu.javeriana.as.personapp.mongo.mapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mongo.document.EstudiosDocument;
import co.edu.javeriana.as.personapp.mongo.document.PersonaDocument;
import co.edu.javeriana.as.personapp.mongo.document.ProfesionDocument;
import lombok.NonNull;

@Mapper
public class EstudiosMapperMongo {

	private final PersonaMapperMongo personaMapperMongo;

	private final ProfesionMapperMongo profesionMapperMongo;

	@Autowired
	public EstudiosMapperMongo(PersonaMapperMongo personaMapperMongo, ProfesionMapperMongo profesionMapperMongo) {
		this.personaMapperMongo = personaMapperMongo;
		this.profesionMapperMongo = profesionMapperMongo;
	}

	public EstudiosDocument fromDomainToAdapter(Study study) {
		EstudiosDocument estudio = new EstudiosDocument();
		estudio.setId(validateId(study.getPerson().getIdentification(), study.getProfession().getIdentification()));
		estudio.setPrimaryPersona(validatePrimaryPersona(study.getPerson()));
		estudio.setPrimaryProfesion(validatePrimaryProfesion(study.getProfession()));
		estudio.setFecha(validateFecha(study.getGraduationDate()));
		estudio.setUniver(validateUniver(study.getUniversityName()));
		return estudio;
	}

	private String validateId(@NonNull Integer identificationPerson, @NonNull Integer identificationProfession) {
		return identificationPerson + "-" + identificationProfession;
	}

	private PersonaDocument validatePrimaryPersona(@NonNull Person person) {
		return personaMapperMongo.fromDomainToAdapter(person);
	}

	private ProfesionDocument validatePrimaryProfesion(@NonNull Profession profession) {
		return profesionMapperMongo.fromDomainToAdapter(profession);
	}

	private LocalDate validateFecha(LocalDate graduationDate) {
		return graduationDate;
	}

	private String validateUniver(String universityName) {
		return universityName != null ? universityName : "";
	}

	public Study fromAdapterToDomain(EstudiosDocument estudiosDocument) {
		Study study = new Study();
		study.setPerson(personaMapperMongo.fromAdapterToDomain(estudiosDocument.getPrimaryPersona()));
		study.setProfession(profesionMapperMongo.fromAdapterToDomain(estudiosDocument.getPrimaryProfesion()));
		study.setGraduationDate(validateGraduationDate(estudiosDocument.getFecha()));
		study.setUniversityName(validateUniversityName(estudiosDocument.getUniver()));
		return null;
	}

	private LocalDate validateGraduationDate(LocalDate fecha) {
		return fecha;
	}

	private String validateUniversityName(String univer) {
		return univer != null ? univer : "";
	}
}