package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.business.requests.rentalRequests.*;
public interface RentalService {
	DataResult<List<ListRentalDto>> getAll();

	Result add(CreateRentalRequest createRentalRequest,List<Integer>additionalPropertyIdentities);

	Result updateRentalReturnDate(UpdateReturnDateRequest updateReturnDateRequest);
	Result update(UpdateRentalRequest updateRentalRequest,List<Integer>additionalPropertyIdentities);
	Result delete(DeleteRentalRequest deleteRentalRequest);


}
