package co.edu.javeriana.as.personapp.model.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaRequest {

	@NotBlank
	private String dni;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String age;

	@NotBlank
	private String gender;

	@NotBlank
	private String database;


}
