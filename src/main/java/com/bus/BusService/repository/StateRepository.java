package com.bus.BusService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.BusService.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
