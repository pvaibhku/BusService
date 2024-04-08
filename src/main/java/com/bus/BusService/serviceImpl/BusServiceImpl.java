package com.bus.BusService.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bus.BusService.entity.Buses;
import com.bus.BusService.entity.City;
import com.bus.BusService.entity.State;
import com.bus.BusService.repository.CityRepository;
import com.bus.BusService.repository.StateRepository;
import com.bus.BusService.response.StateCityResponse;
import com.bus.BusService.service.BusService;

@Service
public class BusServiceImpl implements BusService{
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
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

	@Override
	public List<StateCityResponse> getStates() {
		// TODO Auto-generated method stub
		List<State> states = stateRepository.findAll();
		
		List<StateCityResponse> stateList = modelMapper.map(states, new TypeToken<List<StateCityResponse>>(){}.getType());
		if(CollectionUtils.isEmpty(stateList)) {
			return Collections.EMPTY_LIST;
		}
		Collections.sort(stateList, (s1, s2)-> s1.getName().compareTo(s2.getName()));
		return stateList;
	}

	@Override
	public List<StateCityResponse> getCities(long id) {
		List<City> cities = cityRepository.findByStateId(id);
		
		List<StateCityResponse> cityList = modelMapper.map(cities, new TypeToken<List<StateCityResponse>>(){}.getType());
		if(CollectionUtils.isEmpty(cityList)) {
			return Collections.EMPTY_LIST;
		}
		
		Collections.sort(cityList, (c1,c2)-> c2.getName().compareTo(c1.getName()));
		return cityList;
	}
	
	

}
