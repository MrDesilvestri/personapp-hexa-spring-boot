package co.edu.javeriana.as.personapp.application.port.out;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.domain.Phone;

@Port
public interface PhoneOutputPort {
    public Phone save(Phone person);
	public Boolean delete(Integer numero);
	public Phone findById(Integer numero);
}
