package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.StateService;
import com.etiya.rentACar.business.requests.stateRequests.CreateStateRequest;
import com.etiya.rentACar.business.responses.stateResponses.ListStateDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.StateDao;
import com.etiya.rentACar.entities.State;
@Service
public class StateManager implements StateService {

	private StateDao stateDao;
	   private ModelMapperService modelMapperService;

	    public StateManager(StateDao stateDao, ModelMapperService modelMapperService) {
	        this.stateDao = stateDao;
	        this.modelMapperService = modelMapperService;
	    }


	    @Override
	    public void add(CreateStateRequest createStateRequest) {
	        State state = this.modelMapperService.forRequest().map(createStateRequest, State.class);
	        stateDao.save(state);
	    }

	    @Override
	    public List<ListStateDto> getAll() {
	        List<State> states = this.stateDao.findAll();
	        List<ListStateDto> response = states.stream()
	                .map(state -> this.modelMapperService.forDto().map(state, ListStateDto.class))
	                .collect(Collectors.toList());
	        return response;
	    }

}
