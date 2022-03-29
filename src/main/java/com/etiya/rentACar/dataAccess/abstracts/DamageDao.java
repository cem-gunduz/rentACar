package com.etiya.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.Damage;

@Repository
public interface DamageDao extends JpaRepository<Damage, Integer>{
	 List<Damage> getAllByCarId(int carId);
	 
}
