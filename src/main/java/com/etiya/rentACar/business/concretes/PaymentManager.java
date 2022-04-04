package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalPropertyService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.PaymentService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.PaymentDao;
import com.etiya.rentACar.entities.Payment;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PaymentManager implements PaymentService {
    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private RentalService rentalService;
    private AdditionalPropertyService additionalPropertyService;

    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, CarService carService, RentalService rentalService, AdditionalPropertyService additionalPropertyService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.rentalService = rentalService;
        this.additionalPropertyService = additionalPropertyService;
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
    public double addTotalPrice(CreatePaymentRequest createPaymentRequest, List<Integer>additionalPropertyIdentities) {

        RentalDto rentalDto=this.rentalService.getRentalByCustomerId(createPaymentRequest.getCustomerId());
        int dayDiff = diffDates(createPaymentRequest);
        double paymentTotalPrice = dayDiff * rentalDto.getTotalPrice();
        double additionalPropertyTotalPrice = dayDiff * additionalPropertyTotal(additionalPropertyIdentities);
        double cityDiff = checkCity(createPaymentRequest);
        return (paymentTotalPrice + additionalPropertyTotalPrice + cityDiff);

    }


    public int diffDates(CreatePaymentRequest createPaymentRequest) {
        RentalDto rentalDto=this.rentalService.getRentalByCustomerId(createPaymentRequest.getCustomerId());

        long period = ChronoUnit.DAYS.between(rentalDto.getRentDate(),rentalDto.getReturnDate());
        rentalDto.setRentalDay((int)period);
        return (int)period;

    }

    public double checkCity(CreatePaymentRequest createPaymentRequest) {
        RentalDto rentalDto=this.rentalService.getRentalByCustomerId(createPaymentRequest.getCustomerId());
        if (rentalDto.getRentCityId() != rentalDto.getReturnCityId() ) {
            return rentalDto.getCityFee();
        }
        return 0;


    }

    public double additionalPropertyTotal(List<Integer>additionalPropertyIdentities) {
        double totalPrice = 0;
        //  List<ListAdditionalPropertyDto> additionalPropertyDtoList = this.additionalPropertyService.getById(createRentalRequest.getAdditionalPropertyId());
        for (Integer item : additionalPropertyIdentities) {
            ListAdditionalPropertyDto result = this.additionalPropertyService.getById(item);
            totalPrice += result.getDailyPrice();
        }
        return totalPrice;
    }
}
