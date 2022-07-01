package br.com.sd.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Doctor")
public class Doctor {
	
	//definindo os fields e configurando as colunas
	@Id //define a coluna como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //estratégio de auto-increment
    @Column(name = "id", nullable = false) //nome da coluna e critério para não nula.
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true) //critério para unique
	private String email;
	
	@Column(name = "password")
	private String pass;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String sobrenome;
	
	@Column(name = "mobile_phone", nullable = false)
	private String phone;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
    private List<Crm> CRM;
	
	@Column(name = "authorization_status")
	String authorization_status;
}