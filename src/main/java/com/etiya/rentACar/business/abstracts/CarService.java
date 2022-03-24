package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;


public interface CarService {
	void add(CreateCarRequest createCarRequest);
	List<ListCarDto> getAll();
	List<ListCarDto> getAllByModelYear(double modelYear);
	List<ListCarDto> getAllPaged(int pageNo,int pageSize);
	List<ListCarDto> getAllSorted();
}
