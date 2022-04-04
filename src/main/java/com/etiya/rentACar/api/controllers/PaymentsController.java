package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.PaymentService;
import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class PaymentsController {

    private PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest,@RequestParam("AdditionalPropertyIdentity") List<Integer>additionalPropertyIdentities) {
        return this.paymentService.add(createPaymentRequest,additionalPropertyIdentities);
    }

    @GetMapping("/getall")
    public DataResult<List<ListPaymentDto>> getAll() {
        return this.paymentService.getAll();
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) {
        return this.paymentService.update(updatePaymentRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeletePaymentRequest deletePaymentRequest){
        return this.paymentService.delete(deletePaymentRequest);
    }
}
