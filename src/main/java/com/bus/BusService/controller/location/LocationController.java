package com.bus.BusService.controller.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bus.BusService.entity.State;
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
	
	@PostMapping("/uploadCountryData")
	public ResponseEntity<Response> saveStateAndCityDetails(@RequestParam("file") MultipartFile file){
		
		List<State> stateList =  locationService.saveStateAndCityDetails(file);
		if(CollectionUtils.isEmpty(stateList)) {
			return ResponseEntity.ok().body(new Response<>(false, stateList, "No State - City data inserted"));
		}
		return ResponseEntity.ok().body(new Response<>(true, stateList, "State - City data inserted"));
		
	}
}
