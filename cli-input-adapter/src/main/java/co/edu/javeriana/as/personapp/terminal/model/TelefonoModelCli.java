package co.edu.javeriana.as.personapp.terminal.model;

import co.edu.javeriana.as.personapp.domain.Person;
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
}
