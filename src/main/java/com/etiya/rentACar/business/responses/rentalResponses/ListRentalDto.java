package com.etiya.rentACar.business.responses.rentalResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ListRentalDto {

	private int id;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int carId;
	private int customerId;
	private String firstName;
	private String lastName;

	private int rentCityId;
	private int returnCityId;
	private double cityFee;
	private double totalPrice;
	private double startKilometer;
	private double returnKilometer;
	private int rentalDay;
}
