package co.edu.javeriana.as.personapp.application.port.out;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

@Port
public interface StudyOutputPort {
    Study save(Study study);
    Study edit(Study study);
    boolean delete(String id);
    List<Study> find();
    Study findById(String id);
}
