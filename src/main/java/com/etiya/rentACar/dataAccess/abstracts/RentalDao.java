package com.etiya.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.entities.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {
	Rental getByCarId(int id);
	Rental getById(int id);
	Rental getByCustomerId(int id);
}
