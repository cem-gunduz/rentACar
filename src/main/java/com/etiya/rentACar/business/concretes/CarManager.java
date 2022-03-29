package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
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
	public void add(CreateCarRequest createCarRequest) {

		
		
		
		if(createCarRequest.getState()==CarStates.UnderMaintenance ) {
			throw new BusinessException("Araç zaten bakımda.");
		}
		
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);// ilgili alanları mapler
		this.carDao.save(car);

	}

	@Override
	public List<ListCarDto> getAll() {
		List<Car> cars = this.carDao.findAll();
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public List<ListCarDto> getAllByModelYear(double modelYear) {
		List<Car> cars = this.carDao.getByModelYear(modelYear);
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public List<ListCarDto> getAllPaged(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> cars = this.carDao.findAll(pageable).getContent();
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public List<ListCarDto> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC, "modelYear");
		List<Car> cars = this.carDao.findAll(sort);
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return response;
	}
	 

	@Override
	public CarDto getById(int id) {
		
		Car car=this.carDao.getById(id);
		
		CarDto carDto = this.modelMapperService.forRequest().map(car, CarDto.class);
		return carDto;
	}

	@Override
	public ListCarDto getAllById(int id) {
		
		return null;
	}

	@Override
	public void update(UpdateCarRequest updateCarRequest) {
		checkIfCarExist(updateCarRequest.getId());
		
		Car car= this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		
	}

	@Override
	public void delete(DeleteCarRequest deleteCarRequest) {
		this.carDao.deleteById(deleteCarRequest.getId());
		
		
	}
	
	public void checkIfCarExist(int id) {
		if(this.carDao.getById(id)==null) {
			throw new BusinessException("Böyle bir araba mevcut değil");
		}
	}

	@Override
	public void updateMaintenanceStatus(int id) {
		Car car = carDao.getById(id);
        car.setCarState(CarStates.UnderMaintenance);
        carDao.save(car);
		
	}
	
	
	
	


}
