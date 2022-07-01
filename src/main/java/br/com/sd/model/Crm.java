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

		//definindo os fields e configurando as colunas
		@Id //define a coluna como primary key
	    @GeneratedValue(strategy = GenerationType.IDENTITY) //estratégio de auto-increment
	    @Column(name = "id", nullable = false) //nome da coluna e critério para não nula.
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