package com.etiya.rentACar.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rentals")
@Entity
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="rentDate")
	private LocalDate rentDate;
	
	@Column(name="returnDate")
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	@Column(name="totalPrice")
	private double totalPrice;

	@Column(name="cityFee")
	private double cityFee;
//    @OneToMany(mappedBy = "rental")
//    private List<AdditionalProperty> additionalProperties;
	@ManyToOne
	@JoinColumn(name="additionalProperty_id")
	private AdditionalProperty additionalProperty;


	@ManyToOne
	@JoinColumn(name="returnCityId",referencedColumnName = "id")
	private City returnCity;

	@ManyToOne
	@JoinColumn(name="rentCityId",referencedColumnName = "id")
	private City rentCity;



   /* @ManyToOne
    @JoinColumn(name="city")
    private City city;*/
}
