package com.etiya.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.etiya.rentACar.business.responses.customerResponses.CustomerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	@JsonIgnore
	private int id;
	
	private LocalDate rentDate;
	
	private LocalDate returnDate;
	
	@NotNull
	private int carId;
	@NotNull
	private int customerId;

	@NotNull
	private int rentCityId;

	private int returnCityId;

	private double cityFee;

	//private int additionalPropertyId;
	@JsonIgnore
	private double startKilometer;

	private double returnKilometer;
	//@JsonIgnore
	//private int rentalDay;
}
