package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.ColorService;
import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;

import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.ColorDao;
import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public void add(CreateColorRequest createColorRequest) {
		
		String colorName=createColorRequest.getName().toLowerCase();
				
		checkIfColorExists(colorName);
		
		createColorRequest.setName(colorName);
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
	

	}

	@Override
	public List<ListColorDto> getAll() {
		List<Color> colors = this.colorDao.findAll();
		List<ListColorDto> response = colors.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
				.collect(Collectors.toList());
		return response;
	}
	
	private void checkIfColorExists(String colorName) {
		
		if (colorDao.getByName(colorName).size() == 0) {

			throw new RuntimeException("Girdğiniz renk zaten mevcut.");
		} 

	}

}
