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

import com.etiya.rentACar.business.abstracts.CarDamageService;
import com.etiya.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.etiya.rentACar.business.requests.carDamageRequest.DeleteCarDamageRequest;
import com.etiya.rentACar.business.requests.carDamageRequest.UpdateCarDamageRequest;
import com.etiya.rentACar.business.responses.carDamageResponses.ListCarDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/damages")
public class DamagesController {

	private CarDamageService carDamageService;

	public DamagesController(CarDamageService damageService) {
		this.carDamageService = damageService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarDamageRequest createDamageRequest) {
		return this.carDamageService.add(createDamageRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCarDamageDto>> getAll(){
		return carDamageService.getAll();
	}
	@GetMapping("/getByCarId")
    public DataResult<List<ListCarDamageDto>> getByCarId(@RequestParam("carId") int id ){
        return this.carDamageService.getByCarId(id);
    }
	@GetMapping("/getallpaged")
	public DataResult<List<ListCarDamageDto>> getAllPaged(int pageNo,int pageSize){
		return this.carDamageService.getAllPaged(pageNo,pageSize);
		
	}
	@GetMapping("/getallsorted")
	public DataResult<List<ListCarDamageDto>> getAllSorted(String option,String field){
		return this.carDamageService.getAllSorted(option,field);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest) {
		
		return this.carDamageService.update(updateCarDamageRequest);
	}
	@DeleteMapping("/delete")
	public Result delete(@RequestBody  DeleteCarDamageRequest deleteCarDamageRequest) {
		return this.carDamageService.delete(deleteCarDamageRequest);
	}
	
	
}
