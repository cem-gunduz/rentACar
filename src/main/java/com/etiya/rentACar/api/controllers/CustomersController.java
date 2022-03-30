package com.etiya.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.CustomerService;
import com.etiya.rentACar.business.requests.customerRequests.CreateCustomerRequest;

import com.etiya.rentACar.business.responses.customerResponses.ListCustomerDto;

import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
@RestController
@RequestMapping("/api/customers")
public class CustomersController {
	private CustomerService customerService;
	

	public CustomersController(CustomerService customerService) {
		
		this.customerService = customerService;
	}


	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
		return this.customerService.add(createCustomerRequest);
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<ListCustomerDto>> getAll() {
		return customerService.getAll();
	}
}
