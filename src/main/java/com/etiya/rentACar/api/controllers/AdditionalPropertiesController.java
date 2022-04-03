package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.AdditionalPropertyService;
import com.etiya.rentACar.business.requests.additionalPropertyRequests.CreateAdditionalPropertyRequest;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/additionalproperties")
public class AdditionalPropertiesController {
    private AdditionalPropertyService additionalPropertyService;

    public AdditionalPropertiesController(AdditionalPropertyService additionalPropertyService) {
        this.additionalPropertyService = additionalPropertyService;
    }


    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateAdditionalPropertyRequest createAdditionalPropertyRequest) {
        return this.additionalPropertyService.add(createAdditionalPropertyRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListAdditionalPropertyDto>> getAll() {
        return this.additionalPropertyService.getAll();
    }
}
