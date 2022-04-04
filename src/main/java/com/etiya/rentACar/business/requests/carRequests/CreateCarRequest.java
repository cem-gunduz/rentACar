package com.etiya.rentACar.business.requests.carRequests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.etiya.rentACar.entities.CarStates;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

	@JsonIgnore
	private int id;
	
	@NotNull //dailyPrice zorunlu olarak girilsin
	@Min(1) //min 1 olarak girilebilir
	@Max(200)
	private double dailyPrice;
	
	@NotNull
	@Length(min=2, max = 50)
	private  String description;
	
	@NotNull
	@Min(2015)
	private double modelYear;
	@NotNull
	private int colorId;
	@NotNull
	private int brandId;

	private CarStates state;

	@NotNull
	@Length(min=2)
	private String city;

	@NotNull
	private double carKilometer;
}
