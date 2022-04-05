package com.etiya.rentACar.business.requests.invoiceRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
    private int id;

    private LocalDate createDate;

    private LocalDate rentDate;

    private LocalDate returnDate;
    @JsonIgnore
    private int dayCount;
    @JsonIgnore
    private double totalPrice;

    private int CustomerId;

    private String billNo;
}
