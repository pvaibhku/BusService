package com.bus.BusService.service;

import java.util.List;

import com.bus.BusService.entity.Buses;
import com.bus.BusService.entity.City;
import com.bus.BusService.response.StateCityResponse;

public interface BusService {
	
	public Buses registerBus();

	public List<Buses> fetchAllBuses();

	public List<StateCityResponse> getStates();

	public List<StateCityResponse> getCities(long id);
	

}
