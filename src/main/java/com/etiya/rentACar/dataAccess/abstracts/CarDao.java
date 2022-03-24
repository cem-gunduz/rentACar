package com.etiya.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.Car;


public interface CarDao extends JpaRepository<Car, Integer>{

	List<Car>getByModelYear(double modelYear); //findBy da denilebilir
	List<Car>getByModelYearIn(List<Double> modelYears); //modelyılı 2020 veya 2021 veya.. listye hangi yılları ekelrsen getirir
	List<Car>getByModelYearAndDailyPrice(double modelYear,double dailyPrice);
	List<Car>getByDescriptionContains(String description); //gönderrdiğin ifade içeren alanları verir
	
}
