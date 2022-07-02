package br.com.sd.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoctorDTORequest {
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("surname")
	private String surname;
	
	@JsonProperty("crms")
	private List<CrmDTO> crms;
	
	@JsonProperty("mobile_phone")
	private String mobile_phone;
}
