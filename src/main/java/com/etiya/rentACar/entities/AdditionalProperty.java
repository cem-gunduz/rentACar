package com.etiya.rentACar.entities;


import lombok.Data;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="additionalproperties")
public class AdditionalProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="dailyPrice")
    private double dailyPrice;

//    @ManyToOne
//    @JoinColumn(name = "rental_id")
//    private Rental rental;

    @OneToMany(mappedBy = "additionalProperty")
    private List<Rental> rentals;
}
