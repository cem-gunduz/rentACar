package com.etiya.rentACar.business.requests.cityRequests;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {


    private int id;

    @NotNull
    private String name;

}
