package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.entities.Brand;

public interface BrandService {
	void add(CreateBrandRequest createBrandRequest);
	List<ListBrandDto> getAll();
}
