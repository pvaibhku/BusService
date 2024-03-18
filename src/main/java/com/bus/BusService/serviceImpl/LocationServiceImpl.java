package com.bus.BusService.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bus.BusService.service.LocationService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.Body;

@Service
public class LocationServiceImpl implements LocationService{

	
	
	@Override
	public String getCountryStateAndCityData()  {
		// TODO Auto-generated method stub
		HttpResponse<String> response=null;
		try {
			Map<String, String> map = new HashMap<>();
			map.put("Accept", "application/json");
			map.put("api-token", "5Gfimqteiu8QG0U55pJS00i9SC6Lq37BPqnXCTDzsSI62-jVXbIhRQAWffmB5rTjXX0");
			map.put("user-email", "prakharvaibhav31@gmail.com");
			
			Unirest.setTimeouts(0, 0);
//			response = (HttpResponse<String>) Unirest
//					.get("https://www.universal-tutorial.com/api/getaccesstoken")
//					.headers(map).getBody();
			Body body = Unirest.get( "https://www.universal-tutorial.com/api/getaccesstoken")
			.headers(map).getBody();
			System.out.println("This is text status = " + response.getStatus());
			System.out.println("This is response body = "+ response.getBody());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return response.getBody();
	}

}
