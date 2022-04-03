package com.etiya.rentACar.business.requests.brandRequests;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
	@JsonIgnore
	private int id;
	@NotNull
	@Length(min=1)
	private String name;
}
