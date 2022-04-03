package com.etiya.rentACar.business.requests.additionalPropertyRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CreateAdditionalPropertyRequest {
    @JsonIgnore
    private int id;
    @NotNull
    private String name;

    private double dailyPrice;
}
