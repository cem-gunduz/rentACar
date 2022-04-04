package com.etiya.rentACar.business.responses.paymentResponses;

import java.time.LocalDate;

public class PaymentDto {
    private int id;

    private LocalDate createDate;

    private LocalDate rentDate;
    private LocalDate returnDate;

    private int rentalDay;

    private double totalPrice;

    private String customerName;
}
