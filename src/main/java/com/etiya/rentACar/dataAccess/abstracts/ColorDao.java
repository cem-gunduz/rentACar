package com.etiya.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;
import com.etiya.rentACar.entities.Color;
@Repository
public interface ColorDao extends JpaRepository<Color, Integer>{

	List<Color> getByName(String name);
}
