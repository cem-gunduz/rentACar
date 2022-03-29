package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.requests.brandRequests.DeleteBrandRequest;
import com.etiya.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.BrandDao;
import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.Damage;
import com.etiya.rentACar.entities.Color;

@Service
public class BrandManager implements BrandService{

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	
	
	public BrandManager(BrandDao brandDao,ModelMapperService modelMapperService) {
		
		this.brandDao = brandDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		
		String brandName=createBrandRequest.getName().toLowerCase();
		
		checkIfBrandExists(brandName);
		
		createBrandRequest.setName(brandName);
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		
	}

	@Override
	public List<ListBrandDto> getAll() {
		List<Brand> brands=this.brandDao.findAll();
		List<ListBrandDto>response=brands.stream().map(brand->this.modelMapperService.forDto().map(brand,ListBrandDto.class ))
				.collect(Collectors.toList());
		return response;
	}
	
		private void checkIfBrandExists(String brandName) {
		
		if (brandDao.getByName(brandName).size() != 0) {

			throw new RuntimeException("Girdğiniz marka zaten mevcut.");
		} 

	}

		@Override
		public void update(UpdateBrandRequest updateBrandRequest) {
			
			Brand brand= this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
			this.brandDao.save(brand);
			
		}

		@Override
		public void delete(DeleteBrandRequest deleteBrandRequest) {
			this.brandDao.deleteById(deleteBrandRequest.getId());
			
		}

}
