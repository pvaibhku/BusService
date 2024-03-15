package com.bus.BusService.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bus.BusService.config.JwtHelper;
import com.bus.BusService.entity.BusOwner;
import com.bus.BusService.request.JwtRequest;
import com.bus.BusService.response.JwtResponse;
import com.bus.BusService.service.BusOwnerService;

@Service
public class BusOwnerServiceImpl implements BusOwnerService{

	List<BusOwner> busOwner = new ArrayList<>();
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtHelper helper;
	
	private Logger logger = LoggerFactory.getLogger(BusOwnerServiceImpl.class);
	
	@Override
	public JwtResponse createUser(JwtRequest jwtRequest) {
		// TODO Auto-generated method stub
		
		this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String token = this.helper.generateToken(userDetails);
		
		JwtResponse jwtResponse = JwtResponse.builder().username(jwtRequest.getEmail())
				.jwtToken(token).build();
		
		
		return jwtResponse;
	}

	@Override
	public List<BusOwner> fetchUsers() {
		busOwner = Arrays.asList(new BusOwner(1, "Raj", "raj@mail.com"), new BusOwner(2, "Vishesh", "vishesh@gmail.com"));
		return busOwner;
	}

	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(email, password);
		try {
			this.authenticationManager.authenticate(userNamePassword);
		}
		catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new BadCredentialsException("Invalid creds");
		}
		
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid!";
	}
}
