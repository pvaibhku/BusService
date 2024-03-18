package com.bus.BusService.service;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface LocationService {

	String getCountryStateAndCityData() throws UnirestException;

}
