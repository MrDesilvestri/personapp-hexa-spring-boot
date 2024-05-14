package co.edu.javeriana.as.personapp.mariadb.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mariadb.entity.PersonaEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.TelefonoEntity;

@Mapper
public class TelefonoMapperMaria {

	private final PersonaMapperMaria personaMapperMaria;

	@Autowired
	public TelefonoMapperMaria (PersonaMapperMaria personaMapperMaria) {
		this.personaMapperMaria = personaMapperMaria;
	}

	public TelefonoEntity fromDomainToAdapter(Phone phone) {
		TelefonoEntity entity = new TelefonoEntity();
		entity.setNum(phone.getNumber());
		entity.setOper(phone.getCompany());
		entity.setDuenio(personaMapperMaria.fromDomainToEntity(phone.getOwner()));  // Uso del mapper para convertir
		return entity;
	}

	public Phone fromAdapterToDomain(TelefonoEntity telefonoEntity) {
		Phone phone = new Phone();
		phone.setNumber(telefonoEntity.getNum());
		phone.setCompany(telefonoEntity.getOper());
		phone.setOwner(validateOwner(telefonoEntity.getDuenio()));
		return phone;
	}

	private Person validateOwner(PersonaEntity duenio) {
        if (duenio == null) {
            return null;
        }
        return personaMapperMaria.fromEntityToDomain(duenio);
    }

}