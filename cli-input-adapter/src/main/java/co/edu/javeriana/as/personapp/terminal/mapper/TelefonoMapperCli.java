package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;


@Mapper
public class TelefonoMapperCli {
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
		phone.setNumber(telefonoModelCli.numeroTraducido());
		phone.setCompany(telefonoModelCli.getOperador());
		phone.setOwner(telefonoModelCli.getDuenio());
		return phone;
	}
}
