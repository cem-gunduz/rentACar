package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "createDate")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name="totalPricePayment")
    private double totalPricePayment; //
    @Column(name="rentDate")
    private LocalDate rentDate;
    @Column(name = "returnDate")
    private LocalDate returnDate;
    @Column(name="billNo")
    private String billNo;

    @Column(name = "dayCount")
    private int dayCount;

}
