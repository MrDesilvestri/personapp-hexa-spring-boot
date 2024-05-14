package co.edu.javeriana.as.personapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosRequest {
    private String personId;
    private String professionId;
    private LocalDate graduationDate;
    private String universityName;
    private String database;
}
