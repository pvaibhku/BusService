package com.bus.BusService.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
	
	private Boolean status;
	private T data;
	private String message;
}
