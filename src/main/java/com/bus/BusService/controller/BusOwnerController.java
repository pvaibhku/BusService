package com.bus.BusService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.BusService.entity.Buses;
import com.bus.BusService.request.JwtRequest;
import com.bus.BusService.response.JwtResponse;
import com.bus.BusService.response.Response;
import com.bus.BusService.service.BusOwnerService;
import com.bus.BusService.service.BusService;

@RestController
@RequestMapping("/ownerRegister")
public class BusOwnerController {
	
	@Autowired
	private BusOwnerService busOwnerService;
	
	@Autowired
	private BusService busService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<Response> createUser(@RequestBody JwtRequest jwtRequest){
		
		JwtResponse busOwner =  busOwnerService.createUser(jwtRequest);
		
		return ResponseEntity.ok().body(new Response<>(true, busOwner, "Bus Owner Registered"));
		
	}
	
	
	@GetMapping("/registerBus")
	public ResponseEntity<Response> registerBus(){
		
		Buses buses = busService.registerBus();
		
		return ResponseEntity.ok().body(new Response<>(true, buses, "This is first response"));
	}
	

}
