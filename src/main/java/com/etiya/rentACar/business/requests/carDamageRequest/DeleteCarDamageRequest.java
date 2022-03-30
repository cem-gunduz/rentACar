package com.etiya.rentACar.business.requests.carDamageRequest;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarDamageRequest {

	private int id;
	
	
	private LocalDate accidentDate;
	
	
	private String description;
	
	private int carId;
}
