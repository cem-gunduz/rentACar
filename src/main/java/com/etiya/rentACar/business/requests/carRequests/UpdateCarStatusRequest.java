package com.etiya.rentACar.business.requests.carRequests;

import com.etiya.rentACar.entities.CarStates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarStatusRequest {
	@NotNull
	private int id;
	@NotNull
	private CarStates carState;
	
}
