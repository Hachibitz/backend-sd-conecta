package br.com.sd.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CrmDTO {
	
	@JsonProperty("crm")
	@Column(name = "crm", nullable = false)
	private String crm;
	
	@JsonProperty("uf")
	@Column(name = "uf", nullable = false)
	private String uf;
	
	@JsonProperty("specialty")
	@Column(name = "specialty")
	private String especialidade;
}
