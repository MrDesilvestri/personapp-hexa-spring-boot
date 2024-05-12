package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
public class EstudiosMapperCli {
/* 
    @Autowired
    private PersonaMapperCli personaMapper; // Asumiendo que necesitas convertir Person a PersonaEntity

    public EstudiosEntity fromDomainToEntity(Study study) {
        if (study == null) {
            return null;
        }
        EstudiosEntity entity = new EstudiosEntity();
        entity.setEstudiosPK(new EstudiosEntity(study.getId(), study.getPerson()));
        entity.setFecha(convertDateToSQL(study.getGraduationDate()));
        entity.setUniver(study.getUniversityName());

        // Convertir de Person a PersonaEntity si necesario
        PersonaEntity personaEntity = personaMapper.fromDomainToEntity(study.getPerson());
        entity.setPersona(personaEntity);

        return entity;
    }

    public Study fromEntityToDomain(EstudiosEntity entity) {
        if (entity == null) {
            return null;
        }
        Study study = new Study();
        study.setId(entity.getEstudiosPK().getIdProf());
        study.setPersonId(entity.getEstudiosPK().getCcPer());
        study.setGraduationDate(convertSQLToDate(entity.getFecha()));
        study.setUniversityName(entity.getUniver());

        // Suponiendo que Study tiene un campo para Person
        study.setPerson(personaMapper.fromEntityToDomain(entity.getPersona()));

        return study;
    }

    private java.sql.Date convertDateToSQL(LocalDate localDate) {
        return localDate != null ? new java.sql.Date(localDate.getTime()) : null;
    }

    private Date convertSQLToDate(java.sql.Date date) {
        return date != null ? new Date(date.getTime()) : null;
    }
    */
}
