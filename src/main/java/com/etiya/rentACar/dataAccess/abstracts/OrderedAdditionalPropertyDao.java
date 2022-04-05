package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.OrderedAdditionalProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedAdditionalPropertyDao extends JpaRepository<OrderedAdditionalProperty,Integer> {
}
