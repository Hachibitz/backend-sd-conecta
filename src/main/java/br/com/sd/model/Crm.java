package br.com.sd.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CRM")
public class Crm {

	//definindo os fields e configurando as colunas
		@Id //define a coluna como primary key
	    @GeneratedValue(strategy = GenerationType.IDENTITY) //estratégio de auto-increment
	    @Column(name = "id", nullable = false) //nome da coluna e critério para não nula.
		private Long id;
		
		@Column(name = "crm", nullable = false)
		private String crm;
		
		@Column(name = "uf", nullable = false)
		private String uf;
		
		@Column(name = "specialty")
		private String especialidade;
		
		@ManyToOne(cascade= CascadeType.ALL)
	    @JoinColumn(name = "userID")
		private String userId;
}
