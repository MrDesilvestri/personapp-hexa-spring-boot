package co.edu.javeriana.as.personapp.terminal.adapter;

import org.checkerframework.checker.units.qual.s;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.mapper.TelefonoMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Adapter
public class TelefonoInputAdapterCli {
    @Autowired
	@Qualifier("phoneOutputAdapterMaria")
	private PhoneOutputPort phoneOutputPortMaria;
    /*
     * @Autowired
	@Qualifier("phoneOutputAdapterMongo")
	private PhoneOutputPort phoneOutputPortMongo;
     */
	

    @Autowired
    private TelefonoMapperCli phoneMapperCli;
    @Autowired
	private PhoneInputPort phoneInputPort;

    public void setPhoneOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            phoneInputPort = new PhoneUseCase(phoneOutputPortMaria);
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            //phoneInputPort = new PhoneUseCase(phoneOutputPortMongo);
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public Phone obtenerNumero(Integer identification) throws NoExistException {
        System.out.println("Into obtenerNumero TelefonoEntity in Input Adapter");
        return phoneInputPort.findOne(identification);
    }

    public void crearTelefono(TelefonoModelCli telefono, Person dueno) {
        System.out.println("Into crearTelefono TelefonoEntity in Input Adapter");
        phoneInputPort.create(phoneMapperCli.fromAdapterCliToDomain(telefono, dueno));
    }

    public void editarTelefono(Integer numero, TelefonoModelCli phone, Person dueno) throws NoExistException {
        System.out.println("Into editarTelefono TelefonoEntity in Input Adapter");
        phoneInputPort.edit(numero, phoneMapperCli.fromAdapterCliToDomain(phone, dueno));
    }

    public void eliminarTelefono(Integer identification) throws NoExistException {
        System.out.println("Into eliminarTelefono TelefonoEntity in Input Adapter");
        phoneInputPort.drop(identification);
    }
}