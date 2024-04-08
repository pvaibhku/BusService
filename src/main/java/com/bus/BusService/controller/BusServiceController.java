package com.bus.BusService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.BusService.entity.BusOwner;
import com.bus.BusService.entity.Buses;
import com.bus.BusService.entity.City;
import com.bus.BusService.response.Response;
import com.bus.BusService.response.StateCityResponse;
import com.bus.BusService.service.BusOwnerService;
import com.bus.BusService.service.BusService;

@RestController
@RequestMapping("/handleBusOperation")
public class BusServiceController {
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private BusOwnerService busOwnerService;
	
	
	
	@GetMapping("/listBuses")
	public ResponseEntity<Response> fetchAllBuses(){
		
		List<Buses> busList = busService.fetchAllBuses();
		
		return ResponseEntity.ok().body(new Response<>(true, busList, "This is first response"));
	}
	
	@GetMapping("/fetchUsers")
	public ResponseEntity<Response> fetchUsers(){
		
		List<BusOwner> busOwner =  busOwnerService.fetchUsers();
		
		return ResponseEntity.ok().body(new Response<>(true, busOwner, "Bus Owner Registered"));
		
	}

	@GetMapping("/fetchState")
	public ResponseEntity<Response> getStates(){
		List<StateCityResponse> states = busService.getStates();
		
		return ResponseEntity.ok().body(new Response<>(true, states, "States fetched successfully"));
	}
	
	
	@GetMapping("/fetchCity/{stateId}")
	public ResponseEntity<Response> getCities(@PathVariable("stateId") long id){
		List<StateCityResponse> cities = busService.getCities(id);
		
		return ResponseEntity.ok().body(new Response<>(true, cities, "States fetched successfully"));
	}

}
