package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

@Port
public interface StudyInputPort {
    List<Study> findAll();
    Study findById(String id);
    Study save(Study study);
    Study edit(String id, Study study);
    void drop(String id);
}
