package br.com.sd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrmsDTO {
	
	@JsonProperty("crm")
	private String crm;
	
	@JsonProperty("uf")
	private String uf;
	
	@JsonProperty("specialty")
	private String specialty;
}
