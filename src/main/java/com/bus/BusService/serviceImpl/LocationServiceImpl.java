package com.bus.BusService.serviceImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bus.BusService.entity.City;
import com.bus.BusService.entity.State;
import com.bus.BusService.repository.StateRepository;
import com.bus.BusService.service.LocationService;
import com.mashape.unirest.http.HttpResponse;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	private StateRepository stateRepository;
	
	
	@Override
	public String getCountryStateAndCityData()  {
		// TODO Auto-generated method stub
		HttpResponse<String> response=null;
		try {
//			Map<String, String> map = new HashMap<>();
//			map.put("Accept", "application/json");
//			map.put("api-token", "5Gfimqteiu8QG0U55pJS00i9SC6Lq37BPqnXCTDzsSI62-jVXbIhRQAWffmB5rTjXX0");
//			map.put("user-email", "prakharvaibhav31@gmail.com");
//			
//			Unirest.setTimeouts(0, 0);
////			response = (HttpResponse<String>) Unirest
////					.get("https://www.universal-tutorial.com/api/getaccesstoken")
////					.headers(map).getBody();
//			Body body = Unirest.get( "https://www.universal-tutorial.com/api/getaccesstoken")
//			.headers(map).getBody();
//			System.out.println("This is text status = " + response.getStatus());
//			System.out.println("This is response body = "+ response.getBody());
			
			URL url = new URL("https://api.countrystatecity.in/v1/countries");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("X-CSCAPI-KEY", "bGE4OE1sTkFjRXY0R3h3dXlFVDIxemJVd0p4MmhZYm93N0k4SGVEUw==");
	        connection.setConnectTimeout(5000);
	        connection.setReadTimeout(5000);

	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuilder responseObj = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	        	responseObj.append(line);
	        }
	        reader.close();

	        System.out.println("*****************The response is  = " + responseObj.toString());

	        connection.disconnect();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return response.getBody();
	}

	@Override
	public List<State> saveStateAndCityDetails(MultipartFile file) {
		// TODO Auto-generated method stub
		Map<String, State> map = new HashMap<>();
		Map<String, List<State>> stateMap = new HashMap<>();
		List<State> states = new ArrayList<>();
		try {
			//Workbook workBook = WorkbookFactory.create(file.getInputStream());
			Workbook workBook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workBook.getSheetAt(0);
			
			//Iterator<Row> rowItr = sheet.iterator();
//			while(rowItr.hasNext()) {
//				
//				Row currentRow = rowItr.next();
//				Iterator<Cell> cellItr = currentRow.iterator();
//				
//				State state = new State();
//				List<City> cityList = new ArrayList<>();
//				while(cellItr.hasNext()) {
//					
//					Cell currentCell = cellItr.next();
//					int columnIndex = currentCell.getColumnIndex();
//					
//					switch(columnIndex) {
//					case 0:
//						if(!map.containsKey(currentCell.getStringCellValue())) {
//							state.setName(currentCell.getStringCellValue());
//							map.put(currentCell.getStringCellValue(), state);
//							break;
//						}
//						else {
//							break;
//						}
//					case 1:
//						
//						if(map.containsKey(state.getName())) {
//							List<City> existingCityLst = map.get(state.getName()).getCity();
//							City city = new City();
//							city.setName(currentCell.getStringCellValue());
//							existingCityLst.add(city);
//							
//							State existingState = map.get(state.getName());
//							existingState.setCity(existingCityLst);
//							map.put(state.getName(), existingState);
//						}
//						else {
//							City city = new City();
//							city.setName(currentCell.getStringCellValue());
//							cityList.add(city);
//						}
//						
//						break;
//					}
//				}
//				state.setCity(cityList);
//				if(stateMap.containsKey(state.getName())) {
//					List<State> existingStateList = stateMap.get(state.getName());
//					existingStateList.add(state);
//					stateMap.put(state.getName(), existingStateList);
//				}
//				else {
//					List<State> stateList = new ArrayList<>();
//					stateList.add(state);
//					stateMap.put(state.getName(), stateList);
//				}
//				
//			}
			
			
			
			for (Row row : sheet) {
	            String stateName = row.getCell(0).getStringCellValue();
	            String cityName = row.getCell(1).getStringCellValue();

	            State state = findOrCreateState(states, stateName);
	            City city = new City();
	            city.setName(cityName);
	            city.setState(state);
	            state.getCity().add(city);
	        }

	        workBook.close();
	        //fileInputStream.close();
	        
	       // return states;
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<State> savedStates = stateRepository.saveAll(states);
		return savedStates;
		
		// What is save() and saveFlush()
		// What is use of
	}

	
	private State findOrCreateState(List<State> states, String stateName) {
        for (State state : states) {
            if (state.getName().equals(stateName)) {
                return state;
            }
        }
        State newState = new State();
        newState.setName(stateName);
        newState.setCity(new ArrayList<>());
        states.add(newState);
        return newState;
    }
}
