package co.edu.javeriana.as.personapp.model.response;

import co.edu.javeriana.as.personapp.mariadb.entity.PersonaEntity;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonaResponse extends PersonaRequest{
	
	private String status;
	
	public PersonaResponse(String dni, String firstName, String lastName, String age, String sex, String database, String status) {
		super(dni, firstName, lastName, age, sex, database);
		this.status = status;
	}


}
