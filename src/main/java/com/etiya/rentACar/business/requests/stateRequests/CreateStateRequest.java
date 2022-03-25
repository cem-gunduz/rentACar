package com.etiya.rentACar.business.requests.stateRequests;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStateRequest {

    @JsonIgnore
    private int id;

    private String stateName;
}
