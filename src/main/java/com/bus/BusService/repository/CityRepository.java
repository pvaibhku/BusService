package com.bus.BusService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.BusService.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
