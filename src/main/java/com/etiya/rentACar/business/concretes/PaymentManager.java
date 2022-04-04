package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.PaymentService;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.PaymentDao;
import com.etiya.rentACar.entities.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PaymentManager implements PaymentService {
    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;

    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListPaymentDto>> getAll() {
        List<Payment> payments = this.paymentDao.findAll();
        List<ListPaymentDto> response = payments.stream().map(payment -> this.modelMapperService.forDto().map(payment, ListPaymentDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListPaymentDto>>(response);
    }

    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) {

        Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        this.paymentDao.save(payment);
        return new SuccessResult("Payment added");
    }

    @Override
    public Result update(UpdatePaymentRequest updatePaymentRequest) {
        Payment payment= this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);
        this.paymentDao.save(payment);
        return new SuccessResult("PAYMENT_UPDATE");
    }

    @Override
    public Result delete(DeletePaymentRequest deletePaymentRequest) {
        this.paymentDao.deleteById(deletePaymentRequest.getId());
        return new SuccessResult("PAYMENT_DELETED");
    }
}
