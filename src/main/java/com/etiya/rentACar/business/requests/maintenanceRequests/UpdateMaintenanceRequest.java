package com.etiya.rentACar.business.requests.maintenanceRequests;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {
	
	private int id;

	private LocalDate DateAdded;

	private LocalDate DateReturned;

	private String description;

	private int carId;
}
