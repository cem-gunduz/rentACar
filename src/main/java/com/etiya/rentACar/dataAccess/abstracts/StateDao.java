package com.etiya.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.State;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateDao extends JpaRepository<State, Integer> {


}
