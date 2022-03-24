package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;

import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;


public interface ColorService {
	void add(CreateColorRequest createColorRequest);
	List<ListColorDto> getAll();
	
}
