package com.etiya.rentACar.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="nationalId")
	private String nationalId;
	
	@OneToMany(mappedBy="customer")
	private List<Car> cars;
	
	@OneToMany(mappedBy="customer")
	private List<Rental> rentals;

	@OneToMany(mappedBy="customer")
	private List<Payment> payments;
	
	
}
