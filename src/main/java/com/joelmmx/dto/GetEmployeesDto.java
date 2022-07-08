package com.joelmmx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEmployeesDto {
	@JsonProperty("job_id")
	private Integer jobId;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
}
