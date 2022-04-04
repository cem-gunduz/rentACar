package com.etiya.rentACar.business.abstracts;


import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface PaymentService {

    DataResult<List<ListPaymentDto>> getAll();

    Result add(CreatePaymentRequest createPaymentRequest,List<Integer>additionalPropertyIdentities);
    Result update(UpdatePaymentRequest updatePaymentRequest);
    Result delete(DeletePaymentRequest deletePaymentRequest);

}
