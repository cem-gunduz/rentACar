package com.etiya.rentACar.business.requests.additionalPropertyRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateAdditionalPropertyRequest {
    @JsonIgnore
    private int id;

    private String name;

    private double dailyPrice;
}
