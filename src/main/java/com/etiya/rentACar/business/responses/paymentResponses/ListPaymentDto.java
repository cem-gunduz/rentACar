package com.etiya.rentACar.business.responses.paymentResponses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentDto {

    private int id;

    private LocalDate createDate;

    private LocalDate rentDate;
    private LocalDate returnDate;

    private int rentalDay;

    private double totalPrice;

    private String customerName;


}
