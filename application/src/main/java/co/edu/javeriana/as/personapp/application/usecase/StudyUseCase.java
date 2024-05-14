package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

public class StudyUseCase implements StudyInputPort {

    private final StudyOutputPort studyOutputPort;

    public StudyUseCase(StudyOutputPort studyOutputPort) {
        this.studyOutputPort = studyOutputPort;
    }

    @Override
    public Study save(Study study) {
        return studyOutputPort.save(study);
    }

    @Override
    public Study edit(String id, Study study) {
        return studyOutputPort.save(study); // Assuming save updates if exists
    }

    @Override
    public void drop(String id) {
        studyOutputPort.delete(id);
    }

    @Override
    public List<Study> findAll() {
        return studyOutputPort.find();
    }

    @Override
    public Study findById(String id) {
        return studyOutputPort.findById(id);
    }
}
