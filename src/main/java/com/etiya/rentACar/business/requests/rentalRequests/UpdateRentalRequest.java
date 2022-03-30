package com.etiya.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	@JsonIgnore
	private int id;


	private LocalDate rentDate;
	private LocalDate returnDate;
	@NotNull
	private int carId;
	@NotNull
	private int customerId;
	
}
