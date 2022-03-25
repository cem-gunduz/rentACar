package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.stateRequests.CreateStateRequest;
import com.etiya.rentACar.business.responses.stateResponses.ListStateDto;


import java.util.List;

public interface StateService {
    void add(CreateStateRequest createStateRequest);
    List<ListStateDto> getAll();
}
