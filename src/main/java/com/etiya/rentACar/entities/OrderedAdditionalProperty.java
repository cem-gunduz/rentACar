package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orderedadditionalproperties")
public class OrderedAdditionalProperty {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name="rental_id")
    private Rental rental;
    @ManyToOne
    @JoinColumn(name = "additionalProperty_id")
    private AdditionalProperty additionalProperty;
}
