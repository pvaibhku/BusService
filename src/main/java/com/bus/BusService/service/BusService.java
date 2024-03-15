package com.bus.BusService.service;

import java.util.List;

import com.bus.BusService.entity.Buses;

public interface BusService {
	
	public Buses registerBus();

	public List<Buses> fetchAllBuses();
	

}
