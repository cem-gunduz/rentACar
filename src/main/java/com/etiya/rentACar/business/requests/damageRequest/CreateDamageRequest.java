package com.etiya.rentACar.business.requests.damageRequest;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDamageRequest {

	@JsonIgnore //bunu maplemesin
	private int id;


	@NotNull
	@PastOrPresent
	private LocalDate accidentDate;

	@NotNull
	@Length(min=1,max=50)
	private String description;

	@NotNull
	private int carId;
}
