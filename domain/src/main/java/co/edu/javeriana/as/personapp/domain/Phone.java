package co.edu.javeriana.as.personapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
	@NonNull
	private Integer number;
	@NonNull
	private String company;
	@NonNull
	private Person owner;

	public Integer numeroTraducido(){
		return this.number;
	}
}
