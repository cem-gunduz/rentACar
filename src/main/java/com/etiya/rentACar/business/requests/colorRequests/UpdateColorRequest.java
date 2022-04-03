package com.etiya.rentACar.business.requests.colorRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	@JsonIgnore
	private int id;

	@NotNull
	@Length(min=2)
	private String name;
}
