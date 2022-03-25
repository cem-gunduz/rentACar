package com.etiya.rentACar.api.controllers;


import com.etiya.rentACar.business.abstracts.StateService;
import com.etiya.rentACar.business.requests.stateRequests.CreateStateRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.business.responses.stateResponses.ListStateDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/states")
public class StatesController {
    private StateService stateService;

    public StatesController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/getall")
    public List<ListStateDto> getAll() {
        return stateService.getAll();
    }
    @PostMapping("/add")
    public void add(@RequestBody CreateStateRequest createStateRequest) {
        this.stateService.add(createStateRequest);
    }
}
