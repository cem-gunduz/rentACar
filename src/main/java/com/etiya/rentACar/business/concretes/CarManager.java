package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.damageRequest.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CarDao;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.CarStates;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;

	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {

		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		if(createCarRequest.getState()==CarStates.UnderMaintenance ) {
			throw new BusinessException(BusinessMessages.CarMessage.CAR_MAINTENANCE);
		}
		
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);// ilgili alanları mapler
		this.carDao.save(car);
		return new SuccessResult("BRAND_ADDED");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		this.carDao.deleteById(deleteCarRequest.getId());
		return new SuccessResult("Car deleted");
	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		List<Car> cars = this.carDao.findAll();
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(response);	

	}

	@Override
	public DataResult<List<ListCarDto>> getAllByModelYear(double modelYear) {
		List<Car> cars = this.carDao.getByModelYear(modelYear);
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		 return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> cars = this.carDao.findAll(pageable).getContent();
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		 return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC, "modelYear");
		List<Car> cars = this.carDao.findAll(sort);
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		 return new SuccessDataResult<List<ListCarDto>>(response);
	}
	 

	@Override
	public CarDto getById(int id) {
		
		Car car=this.carDao.getById(id);
		
		CarDto carDto = this.modelMapperService.forRequest().map(car, CarDto.class);
		 return carDto;
	}
	@Override
	public DataResult<CarDto> getByCarId(int id) {
		Car car = this.carDao.getById(id);
		CarDto response = this.modelMapperService.forDto().map(car, CarDto.class);
		return new SuccessDataResult<CarDto>(response);
	}
	@Override
	public DataResult<List<ListCarDto>> getAllById(int id) {
		List<Car> cars =this.carDao.getAllById(id);
		List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		checkIfCarExist(updateCarRequest.getId());
		
		Car car= this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("Car updated");
	}


	@Override
	public void updateCarStatusToAdd(int id) {
		Car car = this.carDao.getById(id);
		car.setCarState(CarStates.UnderMaintenance);
		this.carDao.save(car);
	}

	@Override
	public void checkIfCarAvailable(int id) {
		Car car = carDao.getById(id);
		if(car.getCarState() != CarStates.Available) {
			throw new BusinessException(BusinessMessages.CarStateMessage.CAR_NOT_AVAILABLE);
		}
	}

	@Override
	public DataResult<List<ListCarDto>> getAllByCity(String city) {
		List<Car> cars = this.carDao.getAllByCity(city);
		List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public CarDto getCarKilometer(int id) {
		Car car=this.carDao.getById(id);
		CarDto carDto=this.modelMapperService.forDto().map(car,CarDto.class);
		return carDto;
	}

	@Override
	public void setCarKilometer(CreateRentalRequest createRentalRequest) {
		Car car=this.carDao.getById(createRentalRequest.getCarId());
		car.setCarKilometer(createRentalRequest.getReturnKilometer());
	}


	public void checkIfCarExist(int id) {
		if(this.carDao.getById(id)==null) {
			throw new BusinessException(BusinessMessages.CarMessage.CAR_EXISTS);
		}
	}

	@Override
	public Result updateMaintenanceStatus(int id) {
		Car car = carDao.getById(id);
        car.setCarState(CarStates.UnderMaintenance);
        carDao.save(car);
        
        return new SuccessResult("BRAND_UPDATED");
		
	}
	@Override
	public DataResult<CarDto> updateCarState(UpdateCarStatusRequest updateCarStateRequest) {
		Car car = carDao.getById(updateCarStateRequest.getId());
		car.setCarState(updateCarStateRequest.getCarState());
		this.carDao.save(car);
		CarDto carDto = this.modelMapperService.forDto().map(car, CarDto.class);
		return new SuccessDataResult<CarDto>(carDto);
	}
	@Override
	public void checkIfCarAvaible(int id) {
		Car car = carDao.getById(id);
		if(car.getCarState() == CarStates.UnderMaintenance || car.getCarState() == CarStates.Rented) {
			throw new BusinessException(BusinessMessages.CarStateMessage.CAR_NOT_AVAILABLE);
		} 
	}





}
