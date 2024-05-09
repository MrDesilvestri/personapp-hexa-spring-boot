package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Phone;


@Port
public interface PhoneInputPort {
    
	public Phone create(Phone phone);

	public Phone edit(Integer identification, Phone person) throws NoExistException;

	public Boolean drop(Integer identification) throws NoExistException;
    
    public Phone findOne(Integer identification) throws NoExistException;
}
