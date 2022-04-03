package com.etiya.rentACar.api.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private CityService cityService;

    public CitiesController(CityService cityService) {
        super();
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {
        return this.cityService.add(createCityRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListCityDto>> getAll() {
        return this.cityService.getAll();
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) {
        return this.cityService.update(updateCityRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest){
        return this.cityService.delete(deleteCityRequest);
    }
}
