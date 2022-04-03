package com.etiya.rentACar.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CityDao;
import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.City;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListCityDto>> getAll() {

        List<City> cities = this.cityDao.findAll();
        List<ListCityDto> response = cities.stream().map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCityDto>>(response);

    }

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        checkIfCityExists(createCityRequest.getName());
        City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult("City added");
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) {

        City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult("City added");
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) {
        this.cityDao.deleteById(deleteCityRequest.getId());
        return new SuccessResult("City deleted");
    }
    public void checkIfCityExists(String cityName){
        if (cityDao.existsCityByNameIgnoreCase(cityName)) {
            throw new BusinessException(BusinessMessages.BrandMessage.BRAND_NAME_EXISTS);
        }

    }
}
