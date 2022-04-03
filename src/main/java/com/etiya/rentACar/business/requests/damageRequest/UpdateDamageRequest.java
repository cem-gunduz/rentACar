package com.etiya.rentACar.business.requests.damageRequest;

import java.time.LocalDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDamageRequest {

	@JsonIgnore
	private int id;

	@NotNull
	private LocalDate accidentDate;

	@NotNull
	@Length(min=1,max=50)
	private String description;
	@NotNull
	@PastOrPresent
	private int carId;

}
