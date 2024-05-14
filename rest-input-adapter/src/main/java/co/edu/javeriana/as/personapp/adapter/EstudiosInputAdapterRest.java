package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mapper.EstudiosMapperRest;
import co.edu.javeriana.as.personapp.model.request.EstudiosRequest;
import co.edu.javeriana.as.personapp.model.response.EstudiosResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class EstudiosInputAdapterRest {

    private final StudyOutputPort studyOutputPortMaria;
    private final StudyOutputPort studyOutputPortMongo;
    private final EstudiosMapperRest estudiosMapperRest;
    private StudyInputPort studyInputPort;

    @Autowired
    public EstudiosInputAdapterRest(@Qualifier("studyOutputAdapterMaria") StudyOutputPort studyOutputPortMaria,
                                    @Qualifier("studyOutputAdapterMongo") StudyOutputPort studyOutputPortMongo,
                                    EstudiosMapperRest estudiosMapperRest) {
        this.studyOutputPortMaria = studyOutputPortMaria;
        this.studyOutputPortMongo = studyOutputPortMongo;
        this.estudiosMapperRest = estudiosMapperRest;
    }

    private String setStudyOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            studyInputPort = new StudyUseCase(studyOutputPortMaria);
            return DatabaseOption.MARIA.toString();
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            studyInputPort = new StudyUseCase(studyOutputPortMongo);
            return DatabaseOption.MONGO.toString();
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public List<EstudiosResponse> historial(String database) {
        log.info("Into historial EstudiosEntity in Input Adapter");
        try {
            if (setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
                return studyInputPort.findAll().stream()
                        .map(estudiosMapperRest::fromDomainToAdapterRestMaria)
                        .collect(Collectors.toList());
            } else {
                return studyInputPort.findAll().stream()
                        .map(estudiosMapperRest::fromDomainToAdapterRestMongo)
                        .collect(Collectors.toList());
            }
        } catch (InvalidOptionException e) {
            log.warn(e.getMessage());
            return new ArrayList<>();
        }
    }

    public EstudiosResponse crearEstudios(EstudiosRequest request) {
        try {
            setStudyOutputPortInjection(request.getDatabase());
            Study study = studyInputPort.save(estudiosMapperRest.fromAdapterToDomain(request));
            return estudiosMapperRest.fromDomainToAdapterRestMaria(study);
        } catch (InvalidOptionException e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    public EstudiosResponse actualizarEstudios(String id, EstudiosRequest request) {
        try {
            setStudyOutputPortInjection(request.getDatabase());
            Study study = studyInputPort.edit(id, estudiosMapperRest.fromAdapterToDomain(request));
            return estudiosMapperRest.fromDomainToAdapterRestMaria(study);
        } catch (InvalidOptionException e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    public Study obtenerPorID(String id, String database) {
        try {
            setStudyOutputPortInjection(database);
            return studyInputPort.findById(id);
        } catch (InvalidOptionException e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    public void eliminarEstudios(String id, String database) {
        try {
            setStudyOutputPortInjection(database);
            studyInputPort.drop(id);
        } catch (InvalidOptionException e) {
            log.warn(e.getMessage());
        }
    }
}
