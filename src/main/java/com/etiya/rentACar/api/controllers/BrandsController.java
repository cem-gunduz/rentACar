package com.etiya.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;



@RestController
@RequestMapping("/api/brands")
public class BrandsController {

	private BrandService brandService;

	//@Autowired
	public BrandsController(BrandService brandService) {
		
		this.brandService = brandService;
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
	return this.brandService.add(createBrandRequest);
	}
	@GetMapping("/getall")
	public DataResult<List<ListBrandDto>> getAll() {
		return this.brandService.getAll();	}

}
	


//respponse-request pattern
