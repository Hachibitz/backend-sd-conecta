package br.com.sd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrmsDTO {
	
	@JsonProperty("crm")
	private String crm;
	
	@JsonProperty("uf")
	private String uf;
	
	@JsonProperty("specialty")
	private String specialty;
	
	public CrmsDTO() {
		
	}

	public CrmsDTO(String crm, String uf, String specialty) {
		this.crm = crm;
		this.uf = uf;
		this.specialty = specialty;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
}
