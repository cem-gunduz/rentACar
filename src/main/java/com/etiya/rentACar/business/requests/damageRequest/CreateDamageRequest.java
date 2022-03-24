package com.etiya.rentACar.business.requests.damageRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDamageRequest {

	@JsonIgnore
	private int id;
	private int date;
	private String description;
	private int carId;
}
