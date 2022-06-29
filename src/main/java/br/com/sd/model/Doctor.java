package br.com.sd.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sd.auth.Token;

@Entity
@Table(name = "Doctor")
public class Doctor {
	
	//definindo os fields e configurando as colunas
	@Id //define a coluna como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //estratégio de auto-increment
    @Column(name = "id", nullable = false) //nome da coluna e critério para não nula.
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true) //critério para unique
	private String email;
	
	@Column(name = "password", nullable = false)
	private String pass;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String sobrenome;
	
	@Column(name = "mobile_phone", nullable = false)
	private String phone;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="userId", cascade = CascadeType.ALL)
    private Set<Crm> CRM;
	
	public Doctor() {
		
	}

	public Doctor(Long id, String email, String pass, String name, String sobrenome, String phone, Set<Crm> cRM) {
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.sobrenome = sobrenome;
		this.phone = phone;
		CRM = cRM;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Crm> getCRM() {
		return CRM;
	}

	public void setCRM(Set<Crm> cRM) {
		CRM = cRM;
	}
	
}