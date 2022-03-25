package com.etiya.rentACar.business.responses.maintenanceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListMaintenanceDto {
	private int id;

	private int DateAdded;

	private int DateReturned;

	private String description;

	private int stateId;

	private int carId;
}
