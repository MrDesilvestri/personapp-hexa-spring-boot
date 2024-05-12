package co.edu.javeriana.as.personapp.terminal.model;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.t;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.mapper.TelefonoMapperCli;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefonoModelCli {
    private Integer num;
    private String operador;
    private Person duenio;

    @Autowired
    private PhoneInputPort phoneInputPort;
    
    TelefonoMapperCli phoneMapperCli;

    public String numeroTraducido(){
        return this.num.toString();
    }
    public Integer numeroTraducido(String num){
        try{
            return Integer.parseInt(num);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("El número de teléfono debe ser un número entero.");
        }
        
    }

    public void crearTelefono(TelefonoModelCli telefono, Integer cc) {
        Phone phoneDomain = phoneMapperCli.fromAdapterCliToDomain(telefono, dueno.getIdentification());
        System.out.println("PhoneDomain: " + phoneDomain.toString());
        /* try{
            phoneInputPort.create(phoneDomain);
        }catch(Exception e){
            throw new IllegalArgumentException("El número de teléfono ya existe.");
        }*/
    }

    public Phone obtenerNumero(Integer identification) throws NoExistException {
        System.out.println("Fetching telefono by ID in Input Adapter");
        return phoneInputPort.findOne(identification);
    }

    public void editarTelefono(Integer numero, TelefonoModelCli telefono, Person dueno) throws NoExistException {
        System.out.println("Updating telefono in Input Adapter");
        Phone phoneDomain = phoneMapperCli.fromAdapterCliToDomain(telefono, dueno);
        phoneInputPort.edit(numero, phoneDomain);
    }

    public void eliminarTelefono(Integer identification) throws NoExistException {
        System.out.println("Deleting telefono in Input Adapter");
        phoneInputPort.drop(identification);
    }

    public Integer getNum() {
        return num;
    }
}
