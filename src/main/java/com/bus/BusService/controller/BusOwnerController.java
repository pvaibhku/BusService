package com.bus.BusService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.BusService.request.JwtRequest;
import com.bus.BusService.response.JwtResponse;
import com.bus.BusService.response.Response;
import com.bus.BusService.service.BusOwnerService;

@RestController
@RequestMapping("/ownerRegister")
public class BusOwnerController {
	
	@Autowired
	private BusOwnerService busOwnerService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<Response> createUser(@RequestBody JwtRequest jwtRequest){
		
		JwtResponse busOwner =  busOwnerService.createUser(jwtRequest);
		
		return ResponseEntity.ok().body(new Response<>(true, busOwner, "Bus Owner Registered"));
		
	}
	
	

}
