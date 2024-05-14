package co.edu.javeriana.as.personapp.mongo.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mongo.document.EstudiosDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.EstudiosMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.EstudiosRepositoryMongo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("studyOutputAdapterMongo")
public class StudyOutputAdapterMongo implements StudyOutputPort {

    private final EstudiosRepositoryMongo estudiosRepository;

    private final EstudiosMapperMongo estudiosMapper;

    @Autowired
    public StudyOutputAdapterMongo(EstudiosRepositoryMongo estudiosRepository, EstudiosMapperMongo estudiosMapper) {
        this.estudiosRepository = estudiosRepository;
        this.estudiosMapper = estudiosMapper;
    }

    @Override
    public Study save(Study study) {
        log.debug("Into save on Adapter MongoDB");
        EstudiosDocument persistedDocument = estudiosRepository.save(estudiosMapper.fromDomainToAdapter(study));
        return estudiosMapper.fromAdapterToDomain(persistedDocument);
    }

    @Override
    public Study edit(Study study) {
        log.debug("Into edit on Adapter MongoDB");
        EstudiosDocument persistedDocument = estudiosRepository.save(estudiosMapper.fromDomainToAdapter(study));
        return estudiosMapper.fromAdapterToDomain(persistedDocument);
    }

    @Override
    public boolean delete(String id) {
        log.debug("Into delete on Adapter MongoDB");
        estudiosRepository.deleteById(id);
        return false;
    }

    @Override
    public List<Study> find() {
        log.debug("Into find on Adapter MongoDB");
        return estudiosRepository.findAll().stream()
                .map(estudiosMapper::fromAdapterToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Study findById(String id) {
        log.debug("Into findById on Adapter MongoDB");
        return estudiosRepository.findById(id)
                .map(estudiosMapper::fromAdapterToDomain)
                .orElse(null);
    }
}
