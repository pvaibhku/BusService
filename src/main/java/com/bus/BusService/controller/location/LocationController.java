package com.bus.BusService.controller.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.BusService.response.Response;
import com.bus.BusService.service.LocationService;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/fecthCountryStateAndCity")
	public ResponseEntity<Response> getCountryStateAndCityData() throws UnirestException{
		
		String location = locationService.getCountryStateAndCityData();
		
		return ResponseEntity.ok().body(new Response<>(true, location, "Location details fetched"));
	}
}
