package com.etiya.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.Maintenance;

@Repository
public interface MaintenanceDao extends JpaRepository<Maintenance, Integer> {

	List<Maintenance>getByCarIdAndStateId(int carId,int stateId);
	List<Maintenance>getByCarId(int id);
	List<Maintenance>getByStateId(int id);

}
