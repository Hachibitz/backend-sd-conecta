package br.com.sd.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOKEN")
public class Token {
	
	@Id //define a coluna como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "access_token", nullable = false)
	private String access_token;
	
	@Column(name = "scope")
	private String scope;
	
	@Column(name = "token_type")
	private String token_type;
	
	@Column(name = "expires_in")
	private int expires_in;
	
	public Token() {
		
	}

	public Token(String access_token, int expires_in, String scope, String token_type) {
		this.access_token = access_token;
		this.scope = scope;
		this.token_type = token_type;
		this.expires_in = expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

}
