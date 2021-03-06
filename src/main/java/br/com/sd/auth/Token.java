package br.com.sd.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {

	@JsonProperty("access_token")
	private String access_token;
	
	@JsonProperty("scope")
	private String scope;
	
	@JsonProperty("token_type")
	private String token_type;
	
	@JsonProperty("expires_in")
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
