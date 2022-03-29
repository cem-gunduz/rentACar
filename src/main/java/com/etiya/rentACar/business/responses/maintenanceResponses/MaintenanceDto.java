package com.etiya.rentACar.business.responses.maintenanceResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDto {

	private int id;

	private LocalDate dateAdded;

	private LocalDate dateReturned;

	private String description;


	private int carId;
}
