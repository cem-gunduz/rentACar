package com.etiya.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;



@RestController
@RequestMapping("/api/cars")
public class CarsController {

		private CarService carService;

		public CarsController(CarService carService) {
			
			this.carService = carService;
		}
		
		@PostMapping("/add")
		public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
			return this.carService.add(createCarRequest);
		}
		@GetMapping("/getall")
		public DataResult<List<ListCarDto>> getAll() {
			return this.carService.getAll();
		}
		@GetMapping("/getallbymodelyear")
		public DataResult<List<ListCarDto>> getAllByModelYear(@RequestParam("modelYear") double modelYear) {
			return this.carService.getAllByModelYear(modelYear);
		}
		
		@GetMapping("/getallpaged")
		public DataResult<List<ListCarDto>> getAllPaged(int pageNo,int pageSize){
			return this.carService.getAllPaged(pageNo,pageSize);
			
		}
		@GetMapping("/getallsorted")
		public DataResult<List<ListCarDto>> getAllSorted(){
			return this.carService.getAllSorted();
		}
		
		@PutMapping("/update")
		public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
			return this.carService.update(updateCarRequest);
		}
		
		@PutMapping("/updatecarstate")
		public Result updateCarState(@RequestBody @Valid UpdateCarStatusRequest updateCarStateRequest) {
			return this.carService.updateCarState(updateCarStateRequest);
		}
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
	@GetMapping("/getbycarid")
	public DataResult<CarDto> getByCarId(@RequestParam int id) {
		return this.carService.getByCarId(id);
	}
	@GetMapping("/getallbycity")
	public DataResult<List<ListCarDto>> getAllByCity(@RequestParam ("city") String city){
		return this.carService.getAllByCity(city);
	}
		
	}

