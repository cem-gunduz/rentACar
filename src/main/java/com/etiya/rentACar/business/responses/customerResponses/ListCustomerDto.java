package com.etiya.rentACar.business.responses.customerResponses;

import java.time.LocalDate;

import javax.persistence.Column;

import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ListCustomerDto {
	
	private int id;
	private String firstName;
	private String lastName;
	private String nationalId;

}
