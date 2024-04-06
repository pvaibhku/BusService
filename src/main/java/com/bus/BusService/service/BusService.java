package com.bus.BusService.service;

import java.util.List;

import com.bus.BusService.entity.Buses;
import com.bus.BusService.entity.State;

public interface BusService {
	
	public Buses registerBus();

	public List<Buses> fetchAllBuses();

	public List<State> getStates();
	

}
