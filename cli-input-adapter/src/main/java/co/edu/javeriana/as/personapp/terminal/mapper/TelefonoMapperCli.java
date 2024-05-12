package co.edu.javeriana.as.personapp.terminal.mapper;

import org.springframework.stereotype.Component;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mariadb.entity.TelefonoEntity;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;

@Mapper
@Component
public class TelefonoMapperCli {

	private PersonaMapperCli personaMapperCli;

    public TelefonoModelCli fromDomainToAdapterCli(Phone phone) {
		TelefonoModelCli telefonoModelCli = new TelefonoModelCli();
		telefonoModelCli.setNum(phone.numeroTraducido());
		telefonoModelCli.setOperador(phone.getCompany());
		telefonoModelCli.setDuenio(phone.getOwner());
		return telefonoModelCli;
	}

	
    /*
     * @NonNull
	private String number;
	@NonNull
	private String company;
	@NonNull
	private Person owner;
     */

	public Phone fromAdapterCliToDomain(TelefonoModelCli telefonoModelCli, Person dueno) {
		Phone phone = new Phone();
		phone.setNumber(telefonoModelCli.getNum());
		phone.setCompany(telefonoModelCli.getOperador());
		phone.setOwner(dueno);
		return phone;
	}

	public TelefonoEntity fromDomainToEntityTelefono(Phone phone) {
        TelefonoEntity telefonoEntity = new TelefonoEntity();
        telefonoEntity.setNum(phone.getNumber());
        telefonoEntity.setOper(phone.getCompany());
		telefonoEntity.setDuenio(personaMapperCli.fromDomainToEntity(phone.getOwner()));
        return telefonoEntity;
    }
}
