package com.etiya.rentACar.business.concretes;


import com.etiya.rentACar.business.abstracts.AdditionalPropertyService;
import com.etiya.rentACar.business.requests.additionalPropertyRequests.CreateAdditionalPropertyRequest;
import com.etiya.rentACar.business.requests.rentalRequests.SelectPropertyRequest;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.AdditionalPropertyDto;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.AdditionalPropertyDao;
import com.etiya.rentACar.entities.AdditionalProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalPropertyManager implements AdditionalPropertyService {
    private AdditionalPropertyDao additionalPropertyDao;
    private ModelMapperService modelMapperService;

    public AdditionalPropertyManager(AdditionalPropertyDao additionalPropertyDao, ModelMapperService modelMapperService) {
        this.additionalPropertyDao = additionalPropertyDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListAdditionalPropertyDto>> getAll() {
        List<AdditionalProperty> additionalProperties = this.additionalPropertyDao.findAll();
        List<ListAdditionalPropertyDto> response = additionalProperties.stream().map(additionalProperty -> this.modelMapperService.forDto()
                        .map(additionalProperty, ListAdditionalPropertyDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListAdditionalPropertyDto>>(response);
    }

    @Override
    public Result add(CreateAdditionalPropertyRequest createAdditionalPropertyRequest) {
        AdditionalProperty additionalProperty = this.modelMapperService.forRequest().map(createAdditionalPropertyRequest, AdditionalProperty.class);
        this.additionalPropertyDao.save(additionalProperty);
        return new SuccessResult("Additional property added");
    }





    public ListAdditionalPropertyDto getById(int id){
        AdditionalProperty response = this.additionalPropertyDao.getById(id);
        ListAdditionalPropertyDto result = this.modelMapperService.forDto().map(response, ListAdditionalPropertyDto.class);
        return result;
    }
}
