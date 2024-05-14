package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import org.springframework.stereotype.Component;

import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.EstudiosRequest;
import co.edu.javeriana.as.personapp.model.response.EstudiosResponse;

@Component
public class EstudiosMapperRest {

    public Study fromAdapterToDomain(EstudiosRequest request) {
        Study study = new Study();
        study.setPerson(new Person(Integer.parseInt(request.getPersonId())));
        study.setProfession(new Profession(Integer.parseInt(request.getProfessionId())));
        study.setGraduationDate(request.getGraduationDate());
        study.setUniversityName(request.getUniversityName());
        return study;
    }

    public EstudiosResponse fromDomainToAdapterRestMaria(Study study) {
        return new EstudiosResponse(
                study.getPerson().getIdentification().toString(),
                study.getProfession().getIdentification().toString(),
                study.getGraduationDate() != null ? study.getGraduationDate().toString() : null,
                study.getUniversityName(),
                "MARIA",
                "Success"
        );
    }

    public EstudiosResponse fromDomainToAdapterRestMongo(Study study) {
        return new EstudiosResponse(
                study.getPerson().getIdentification().toString(),
                study.getProfession().getIdentification().toString(),
                study.getGraduationDate() != null ? study.getGraduationDate().toString() : null,
                study.getUniversityName(),
                "MONGO",
                "Success"
        );
    }
}
