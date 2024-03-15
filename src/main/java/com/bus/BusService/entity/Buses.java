package com.bus.BusService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Buses {
	
	private long id;
	private String busNumber;
	private String rcNumber;
	private String location;
}
