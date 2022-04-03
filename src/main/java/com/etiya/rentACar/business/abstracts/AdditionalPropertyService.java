package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.additionalPropertyRequests.CreateAdditionalPropertyRequest;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface AdditionalPropertyService {
    DataResult<List<ListAdditionalPropertyDto>> getAll();

    Result add(CreateAdditionalPropertyRequest createAdditionalPropertyRequest);

    ListAdditionalPropertyDto getById(int id);
}
