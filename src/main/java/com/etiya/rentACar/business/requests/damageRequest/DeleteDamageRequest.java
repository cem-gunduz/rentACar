package com.etiya.rentACar.business.requests.damageRequest;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDamageRequest {

	private int id;
	
	
	private LocalDate accidentDate;
	
	
	private String description;
	
	private int carId;
}
