package co.edu.javeriana.as.personapp.model.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosResponse {
    private String personId;
    private String professionId;
    private String graduationDate;
    private String universityName;
    private String database;
    private String status;
}
