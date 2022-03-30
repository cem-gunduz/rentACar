package com.etiya.rentACar.business.requests.carDamageRequest;

import java.time.LocalDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {

	
	private int id;
	
	
	private LocalDate accidentDate;
	
	
	private String description;
	
	private int carId;

}
