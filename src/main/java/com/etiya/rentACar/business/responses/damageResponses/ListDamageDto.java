package com.etiya.rentACar.business.responses.damageResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDamageDto {

	private int id;
	
	
	private LocalDate accidentDate;
	
	
	private String description;
	
	private String carDescription;
	
	private int carId;
}
