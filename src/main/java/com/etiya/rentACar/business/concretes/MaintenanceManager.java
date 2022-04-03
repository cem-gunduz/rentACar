package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.MaintenanceDao;
import com.etiya.rentACar.entities.Maintenance;
@Service
public class MaintenanceManager implements MaintenanceService {

	private MaintenanceDao maintenanceDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	public MaintenanceManager(MaintenanceDao maintenanceDao, ModelMapperService modelMapperService, CarService carService) {
		this.maintenanceDao = maintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carService=carService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		
		checkIfCarExistsInMaintenance(createMaintenanceRequest.getCarId());
		
        Maintenance carMaintenance= this.modelMapperService.forRequest().
                map(createMaintenanceRequest, Maintenance.class);

        CarDto car=carService.getById(createMaintenanceRequest.getCarId());
        
        UpdateCarRequest updateCarRequest=modelMapperService.forRequest().map(car, UpdateCarRequest.class);
        
        carService.updateMaintenanceStatus(updateCarRequest.getId());
        
        
       this.maintenanceDao.save(carMaintenance);
       
       return new SuccessResult("BRAND_ADDED");
       
     

	}

	@Override
	public DataResult<List<ListMaintenanceDto>> getAll() {
		List<Maintenance> maintenances = this.maintenanceDao.findAll();
		List<ListMaintenanceDto> response = maintenances.stream()
				.map(maintenance -> this.modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListMaintenanceDto>>(response);
	}

	@Override
	public DataResult<List<ListMaintenanceDto>> getByCarId(int id) {
		List<Maintenance> maintenances = this.maintenanceDao.getByCarId(id);
		List<ListMaintenanceDto> response = maintenances.stream()
				.map(maintenance -> this.modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListMaintenanceDto>>(response);
	}


	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		checkIfCarExist(updateMaintenanceRequest.getCarId());
		
		Maintenance carMaintenance= this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		this.maintenanceDao.save(carMaintenance);
		  return new SuccessResult("BRAND_UPDATED");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		
		this.maintenanceDao.deleteById(deleteMaintenanceRequest.getId());
		  return new SuccessResult("BRAND_DELETED");
	}
	
	
	public void checkIfCarExist(int id) {
		if(this.carService.getById(id)==null) {
			throw new BusinessException(BusinessMessages.MaintenanceMessages.CAR_NOT_EXISTS);
		}
	}
	
	public void checkIfCarExistsInMaintenance(int carId) {
		if(this.maintenanceDao.existsMaintenanceByCarId(carId)) {
			throw new BusinessException(BusinessMessages.MaintenanceMessages.CAR_UNDER_MAINTENANCE);
		}
	}
	


	

	

}
