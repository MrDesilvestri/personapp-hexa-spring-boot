package co.edu.javeriana.as.personapp.terminal.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoModelCli {
    private String num;
    private String operador;
    private Integer duenio;
    private PersonaModelCli duenioModelCli;

}
