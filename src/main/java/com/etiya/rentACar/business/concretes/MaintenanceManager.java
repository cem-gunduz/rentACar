package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.stateRequests.CreateStateRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.MaintenanceDao;
import com.etiya.rentACar.entities.Maintenance;
import com.etiya.rentACar.entities.State;
@Service
public class MaintenanceManager implements MaintenanceService {

	private MaintenanceDao maintenanceDao;
	private ModelMapperService modelMapperService;

	public MaintenanceManager(MaintenanceDao maintenanceDao, ModelMapperService modelMapperService) {
		this.maintenanceDao = maintenanceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public void add(CreateMaintenanceRequest createMaintenanceRequest) {
		checkIfStateExist(createMaintenanceRequest.getCarId());

        Maintenance maintenance=this.modelMapperService.forRequest().map(createMaintenanceRequest,Maintenance.class);
        maintenanceDao.save(maintenance);

	}

	@Override
	public List<ListMaintenanceDto> getAll() {
		List<Maintenance> maintenances = this.maintenanceDao.findAll();
		List<ListMaintenanceDto> response = maintenances.stream()
				.map(maintenance -> this.modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public List<ListMaintenanceDto> getByCarId(int id) {
		List<Maintenance> maintenances = this.maintenanceDao.getByCarId(id);
		List<ListMaintenanceDto> response = maintenances.stream()
				.map(maintenance -> this.modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
				.collect(Collectors.toList());
		return response;
	}
	@Override
    public List<ListMaintenanceDto> getByStateId(int id) {
        List<Maintenance> maintenances=this.maintenanceDao.getByStateId(id);

        List<ListMaintenanceDto> responce=maintenances.stream()
                .map(maintenance -> this.modelMapperService.forDto().map(maintenance,ListMaintenanceDto.class))
                .collect(Collectors.toList());
        return responce;
    }

    private void checkIfStateExist(int carId){
        List<Maintenance> maintenances=this.maintenanceDao.getByCarIdAndStateId(carId,1);
        if(maintenances.size()!=0){
            throw new BusinessException("araba zaten var");
        }
    }
	

}
