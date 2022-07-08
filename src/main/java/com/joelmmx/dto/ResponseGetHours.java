package com.joelmmx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseGetHours extends GenericResponse{
	
	@JsonProperty("total_worked_hours")
	private Integer totalWorkedHours;

	public Integer getTotalWorkedHours() {
		return totalWorkedHours;
	}

	public void setTotalWorkedHours(Integer totalWorkedHours) {
		this.totalWorkedHours = totalWorkedHours;
	}
	
	
}
