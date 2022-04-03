package com.etiya.rentACar.business.requests.maintenanceRequests;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceRequest {

	@JsonIgnore
	private int id;

	@NotNull
	@FutureOrPresent
	private LocalDate DateAdded;
	@NotNull
	@FutureOrPresent
	private LocalDate DateReturned;

	private String description;
	@NotNull
	private int carId;
}
