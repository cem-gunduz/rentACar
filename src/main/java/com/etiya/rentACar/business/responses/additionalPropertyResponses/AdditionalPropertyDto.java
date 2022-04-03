package com.etiya.rentACar.business.responses.additionalPropertyResponses;



import com.etiya.rentACar.entities.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdditionalPropertyDto {

    private int id;

    private String name;

    private double dailyPrice;

}
