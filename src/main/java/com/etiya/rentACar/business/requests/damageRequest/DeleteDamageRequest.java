package com.etiya.rentACar.business.requests.damageRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDamageRequest {
	@NotNull
	private int id;

}
