package br.com.sd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "CRM")
@Data
public class Crm {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
		private Long id;
		
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