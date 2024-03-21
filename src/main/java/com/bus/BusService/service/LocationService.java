package com.bus.BusService.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bus.BusService.entity.State;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface LocationService {

	String getCountryStateAndCityData() throws UnirestException;

	List<State> saveStateAndCityDetails(MultipartFile file);

}
