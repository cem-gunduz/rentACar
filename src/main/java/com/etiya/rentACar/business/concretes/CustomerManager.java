package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CustomerService;
import com.etiya.rentACar.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.business.responses.customerResponses.ListCustomerDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CustomerDao;
import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.Customer;
@Service
public class CustomerManager implements CustomerService{
	
	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;

	public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
		this.customerDao = customerDao;
		this.modelMapperService = modelMapperService;
	}
	

	@Override
	public Result add(CreateCustomerRequest createCustomerRequest) {
		
		Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
		this.customerDao.save(customer);

		return new SuccessResult("CUSTOMER_ADDED");
	}

	@Override
	public DataResult<List<ListCustomerDto>> getAll() {
		List<Customer> customers = this.customerDao.findAll();
		List<ListCustomerDto> response = customers.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCustomerDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCustomerDto>>(response);	

	}

}
