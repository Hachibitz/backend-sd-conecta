package br.com.sd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoctorDTOResponse {
	
	@JsonProperty("access_token")
	private String access_token;
	
	@JsonProperty("refresh_token")
	private String refresh_token;
	
	@JsonProperty("authorization_status")
	private String authorization_status;

}