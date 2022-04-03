package com.etiya.rentACar.business.responses.additionalPropertyResponses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdditionalPropertyDto {

    private int id;

    private String name;

    private double dailyPrice;
}
