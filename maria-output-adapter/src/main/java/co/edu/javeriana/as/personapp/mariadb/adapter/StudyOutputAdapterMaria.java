package co.edu.javeriana.as.personapp.mariadb.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.mapper.EstudiosMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.EstudiosRepositoryMaria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("studyOutputAdapterMaria")
public class StudyOutputAdapterMaria implements StudyOutputPort {

    private final EstudiosRepositoryMaria estudiosRepository;


    private final EstudiosMapperMaria estudiosMapper;

    @Autowired
    public StudyOutputAdapterMaria(EstudiosRepositoryMaria estudiosRepository, EstudiosMapperMaria estudiosMapper) {
        this.estudiosRepository = estudiosRepository;
        this.estudiosMapper = estudiosMapper;
    }

    @Override
    public Study save(Study study) {
        log.debug("Into save on Adapter MariaDB");
        EstudiosEntity persistedEntity = estudiosRepository.save(estudiosMapper.fromDomainToAdapter(study));
        return estudiosMapper.fromAdapterToDomain(persistedEntity);
    }

    @Override
    public Study edit(Study study) {
        log.debug("Into edit on Adapter MariaDB");
        EstudiosEntity persistedEntity = estudiosRepository.save(estudiosMapper.fromDomainToAdapter(study));
        return estudiosMapper.fromAdapterToDomain(persistedEntity);
    }

    @Override
    public boolean delete(String id) {
        log.debug("Into delete on Adapter MariaDB");
        estudiosRepository.deleteById(id);
        return estudiosRepository.findById(id).isEmpty();
    }

    @Override
    public List<Study> find() {
        log.debug("Into find on Adapter MariaDB");
        return estudiosRepository.findAll().stream()
                .map(estudiosMapper::fromAdapterToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Study findById(String id) {
        log.debug("Into findById on Adapter MariaDB");
        return estudiosRepository.findById(id)
                .map(estudiosMapper::fromAdapterToDomain)
                .orElse(null);
    }
}
