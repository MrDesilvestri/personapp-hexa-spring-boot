package co.edu.javeriana.as.personapp.terminal.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaModelCli {
    private Integer cc;
    private String nombre;
    private String apellido;
    private String genero;
    private int edad;

}
