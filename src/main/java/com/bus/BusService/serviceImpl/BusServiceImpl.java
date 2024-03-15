package com.bus.BusService.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bus.BusService.entity.Buses;
import com.bus.BusService.service.BusService;

@Service
public class BusServiceImpl implements BusService{
	
	Buses buses = new Buses();
	
	List<Buses> busList = new ArrayList<>();
	
	@Override
	public Buses registerBus() {
		
		buses.setId(1);
		buses.setBusNumber("DH76SH8");
		buses.setLocation("Gurgaon");
		buses.setRcNumber("S8HEYFSBJSYU8");
		
		return buses;
	}

	@Override
	public List<Buses> fetchAllBuses() {
		// TODO Auto-generated method stub
		
		buses = new Buses(1, "DH76SH8", "Gurgaon", "S8HEYFSBJSYU8");
		busList.add(buses);
		
		buses = new Buses(2, "DH76SHR8", "Gurgaon", "S8HEYFSS93JSYU8");
		busList.add(buses);
		
		return busList;
	}
	
	

}
