package com.etiya.rentACar.api.controllers;

import java.util.List;

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

@RestController
@RequestMapping("/api/damages")
public class DamagesController {

	private DamageService damageService;

	public DamagesController(DamageService damageService) {
		this.damageService = damageService;
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateDamageRequest createDamageRequest) {
		this.damageService.add(createDamageRequest);
	}
	
	@GetMapping("/getall")
	public List<ListDamageDto> getAll(){
		return damageService.getAll();
	}
	@GetMapping("/getByCarId")
    public List<ListDamageDto> getByCarId(@RequestParam("carId") int id ){
        return this.damageService.getByCarId(id);
    }
	@GetMapping("/getallpaged")
	public List<ListDamageDto> getAllPaged(int pageNo,int pageSize){
		return this.damageService.getAllPaged(pageNo,pageSize);
		
	}
	@GetMapping("/getallsorted")
	public List<ListDamageDto> getAllSorted(String option,String field){
		return this.damageService.getAllSorted(option,field);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody UpdateDamageRequest updateDamageRequest) {
		
		this.damageService.update(updateDamageRequest);
	}
	@DeleteMapping("/delete")
	public void delete(@RequestBody DeleteDamageRequest deleteDamageRequest) {
		this.damageService.delete(deleteDamageRequest);
	}
	
	
}
