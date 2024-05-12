package co.edu.javeriana.as.personapp.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {
	@NonNull
	private Integer identification;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private Gender gender;
	private Integer age;
	@ToString.Exclude
	private List<Phone> phoneNumbers;
	@ToString.Exclude
	private List<Study> studies;

	public Boolean isValidAge() {
		return this.age >= 0;
	}

	public List<Phone> getTelefonos() {
        return phoneNumbers;
    }

    public void setTelefonos(List<Phone> telefonos) {
        this.phoneNumbers = telefonos;
    }


	public Gender mapeoGenero(String genero) {
		switch(genero) {
			case "MALE":
				return  Gender.MALE;
			case "FEMALE":
				return Gender.FEMALE;
				case "OTHER":
				return Gender.OTHER;
			default:
				throw new IllegalArgumentException("Género inválido: . Los valores válidos son: MALE, FEMALE, OTHER.");
		}
	}
}
