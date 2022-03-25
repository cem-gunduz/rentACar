package com.etiya.rentACar.business.responses.stateResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListStateDto {

    private int id;

    private String stateName;
}
