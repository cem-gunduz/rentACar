package com.etiya.rentACar.business.requests.brandRequests;

import java.time.LocalDate;

import com.etiya.rentACar.business.requests.damageRequest.CreateDamageRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBrandRequest {
	private int id;
}
