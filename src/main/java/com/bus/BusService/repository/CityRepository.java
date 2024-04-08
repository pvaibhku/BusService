package com.bus.BusService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bus.BusService.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

	//@Query(value="select * from city c ")
	List<City> findByStateId(long id);

}
