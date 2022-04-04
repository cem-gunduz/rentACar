package com.etiya.rentACar.business.requests.paymentRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
    @JsonIgnore
    private int id;

    private LocalDate createDate;
    @JsonIgnore
    private LocalDate rentDate;
    @JsonIgnore
    private LocalDate returnDate;
    @JsonIgnore
    private int rentalDay;
    @JsonIgnore
    private double totalPrice;
    @JsonIgnore
    private String customerName;
}
