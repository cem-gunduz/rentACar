package com.etiya.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequest.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequest.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequest.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/damages")
public class DamagesController {

	private DamageService damageService;

	public DamagesController(DamageService damageService) {
		this.damageService = damageService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateDamageRequest createDamageRequest) {
		return this.damageService.add(createDamageRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListDamageDto>> getAll(){
		return this.damageService.getAll();
	}
	@GetMapping("/getByCarId")
    public DataResult<List<ListDamageDto>> getByCarId(@RequestParam("carId") int id ){
        return this.damageService.getByCarId(id);
    }
	@GetMapping("/getallpaged")
	public DataResult<List<ListDamageDto>> getAllPaged(int pageNo, int pageSize){
		return this.damageService.getAllPaged(pageNo,pageSize);
		
	}
	@GetMapping("/getallsorted")
	public DataResult<List<ListDamageDto>> getAllSorted(String option, String field){
		return this.damageService.getAllSorted(option,field);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateDamageRequest updateCarDamageRequest) {
		
		return this.damageService.update(updateCarDamageRequest);
	}
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteDamageRequest deleteCarDamageRequest) {
		return this.damageService.delete(deleteCarDamageRequest);
	}
	
	
}
