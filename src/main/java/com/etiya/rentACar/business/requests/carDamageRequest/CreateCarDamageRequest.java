package com.etiya.rentACar.business.requests.carDamageRequest;

import java.time.LocalDate;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {

	@JsonIgnore //bunu maplemesin
	private int id;
	
	
	private LocalDate accidentDate;
	
	
	private String description;
	
	private int carId;
}
