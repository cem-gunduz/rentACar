package com.etiya.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {
    City getById(int id);
    boolean existsCityByNameIgnoreCase(String cityName);
}
