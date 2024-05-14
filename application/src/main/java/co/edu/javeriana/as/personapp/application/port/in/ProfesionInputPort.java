package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Profession;

import java.util.List;

@Port
public interface ProfesionInputPort {
    public void setPersintence(ProfessionOutputPort profesionPersintence);
    public Profession create(Profession profesion);
    public Profession edit(Integer id, Profession profesion) throws NoExistException;
    public Boolean drop(Integer id) throws NoExistException;
    public List<Profession> findAll();
    public Profession findOne(Integer id) throws NoExistException;
    public Profession findById(Integer id);
    public Integer count();
}
