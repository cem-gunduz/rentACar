package com.etiya.rentACar.business.requests.maintenanceRequests;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceRequest {

	@JsonIgnore
	private int id;

	private int DateAdded;

	private int DateReturned;

	private String description;

	private int stateId;

	private int carId;
}
