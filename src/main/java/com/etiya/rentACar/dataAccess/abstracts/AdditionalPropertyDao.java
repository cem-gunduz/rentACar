package com.etiya.rentACar.dataAccess.abstracts;
import com.etiya.rentACar.entities.AdditionalProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalPropertyDao extends JpaRepository<AdditionalProperty,Integer> {


    AdditionalProperty getById(int id);
}
