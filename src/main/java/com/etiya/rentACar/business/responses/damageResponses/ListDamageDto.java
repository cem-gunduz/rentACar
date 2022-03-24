package com.etiya.rentACar.business.responses.damageResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDamageDto {

	private int id;
	private int date;
	private String description;
	private int carId;
}
