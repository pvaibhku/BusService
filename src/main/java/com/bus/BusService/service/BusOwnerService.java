package com.bus.BusService.service;

import java.util.List;

import com.bus.BusService.entity.BusOwner;
import com.bus.BusService.request.JwtRequest;
import com.bus.BusService.response.JwtResponse;

public interface BusOwnerService {

	JwtResponse createUser(JwtRequest jwtRequest);

	List<BusOwner> fetchUsers();

}
