package com.etiya.rentACar.business.concretes;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.AdditionalPropertyService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.additionalPropertyRequests.CreateAdditionalPropertyRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.rentalRequests.*;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.AdditionalPropertyDto;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.entities.AdditionalProperty;
import com.etiya.rentACar.entities.CarStates;
import org.apache.tomcat.jni.Local;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.RentalDao;
import com.etiya.rentACar.entities.Rental;

@Service
public class RentalManager implements RentalService {
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private AdditionalPropertyService additionalPropertyService;

	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService, AdditionalPropertyService additionalPropertyService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.additionalPropertyService = additionalPropertyService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		carService.checkIfCarAvailable(createRentalRequest.getCarId());
		checkKilometer(createRentalRequest);//
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setReturnDate(null);
		//rental.setTotalPrice(addTotalPrice(createRentalRequest,additionalPropertyIdentities));

		this.rentalDao.save(rental);

		updateKilometer(createRentalRequest);
		lastKilometer(createRentalRequest);
		UpdateCarStatusRequest updateCarStatusRequest = new UpdateCarStatusRequest();
		updateCarStatusRequest.setId(createRentalRequest.getCarId());
		updateCarStatusRequest.setCarState(CarStates.Rented);
		carService.updateCarState(updateCarStatusRequest);
		return new SuccessResult("RENTAL_CAR_ADDED");

	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {

		List<Rental> rentals = this.rentalDao.findAll();
		List<ListRentalDto> response = rentals.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListRentalDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	public Result updateRentalReturnDate(UpdateReturnDateRequest updateReturnDateRequest) {

		Rental rental = this.rentalDao.getByCarId(updateReturnDateRequest.getCarId());
		rental.setReturnDate(updateReturnDateRequest.getReturnDate());
		rentalDao.save(rental);
		return new SuccessResult("Rental Return Date Updated");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		CreateRentalRequest createRentalRequest = this.modelMapperService.forRequest().map(updateRentalRequest, CreateRentalRequest.class);
		//addTotalPrice(createRentalRequest,additionalPropertyIdentities);
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);

		return new SuccessResult("RENTAL_CAR_UPDATED");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		this.rentalDao.deleteById(deleteRentalRequest.getId());
		return new SuccessResult("Rental deleted");
	}


	public void updateKilometer(CreateRentalRequest createRentalRequest){
		CarDto carDto=this.carService.getCarKilometer(createRentalRequest.getCarId());
		Rental rental=this.rentalDao.getByCarId(createRentalRequest.getCarId());
		rental.setStartKilometer(carDto.getCarKilometer());

	}
	public Result lastKilometer(CreateRentalRequest createRentalRequest){
		this.carService.setCarKilometer(createRentalRequest);
	return new SuccessResult("Arabanın kilometresi güncellendi");
	}
	public void checkKilometer(CreateRentalRequest createRentalRequest){ //
		CarDto carDto=this.carService.getCarKilometer(createRentalRequest.getCarId());
		if(createRentalRequest.getReturnKilometer()<carDto.getCarKilometer()){
			throw new BusinessException("!!");

		}

	}
	@Override
	public RentalDto getRentalByCarId(int id){
		Rental rental=this.rentalDao.getByCarId(id);
		RentalDto rentalDto=this.modelMapperService.forDto().map(rental,RentalDto.class);
		return rentalDto;
	}

	@Override
	public RentalDto getRentalByCustomerId(int id) {
		Rental rental=this.rentalDao.getByCustomerId(id);
		RentalDto rentalDto=this.modelMapperService.forDto().map(rental,RentalDto.class);
		return rentalDto;
	}
	public double checkCity(CreateRentalRequest createRentalRequest) {
		if (createRentalRequest.getRentCityId() != createRentalRequest.getReturnCityId()) {
			return createRentalRequest.getCityFee();
		}
		return 0;
	}

	public int diffDates(CreateRentalRequest createRentalRequest) {
		Rental rental=this.rentalDao.getByCustomerId(createRentalRequest.getCustomerId());

		long period = ChronoUnit.DAYS.between(rental.getRentDate(),rental.getReturnDate());
		rental.setRentalDay((int)period);
		return (int)period;

	}


}
