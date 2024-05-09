package co.edu.javeriana.as.personapp.application.usecase;

import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Phone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
public class PhoneUseCase implements PhoneInputPort{

    private PhoneOutputPort phonePersintence;

    public PhoneUseCase(@Qualifier("phoneOutputAdapterMaria") PhoneOutputPort phonePersintence) {
		this.phonePersintence=phonePersintence;
	}

    public void setPersintence(PhoneOutputPort phonePersintence) {
        this.phonePersintence=phonePersintence;
    }

    //public Phone findOne(Integer identification) throws NoExistException;
    @Override
	public Phone findOne(Integer identification) throws NoExistException {
		Phone oldPhone = phonePersintence.findById(identification);
		if (oldPhone != null)
			return oldPhone;
        throw new NoExistException("El telefono con id " + identification + " no existe en la base de datos, no se puede editar");
	}

    @Override
    public Phone create(Phone phone) {
        log.debug("Into create on Application Domain");
        return phonePersintence.save(phone);
    }

    @Override
    public Phone edit(Integer identification, Phone person) throws NoExistException {
        Phone oldPhone = phonePersintence.findById(identification);
        if(oldPhone != null)
            return phonePersintence.save(person);
        throw new NoExistException("El telefono con id " + identification + " no existe en la base de datos, no se puede editar");
    }

    @Override
    public Boolean drop(Integer identification) throws NoExistException {
        Phone oldPhone = phonePersintence.findById(identification);
        if(oldPhone != null)
            return phonePersintence.delete(identification);
            throw new NoExistException("El telefono con id " + identification + " no existe en la base de datos, no se puede eliminar");
    }
}
