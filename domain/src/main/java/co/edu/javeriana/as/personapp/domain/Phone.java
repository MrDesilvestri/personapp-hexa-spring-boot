package co.edu.javeriana.as.personapp.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Phone {
	@NonNull
	private String number;
	@NonNull
	private String company;
	@NonNull
	private Integer duenio;
	@NonNull
	private Person owner;
}
