package br.com.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sd.auth.Token;
import br.com.sd.auth.TokenRequest;
import br.com.sd.model.DoctorDTO;
import br.com.sd.proxy.DoctorProxy;

@Service
public class DoctorService {
	
	@Autowired
	DoctorProxy proxy;
	
	public Token getToken() {
		TokenRequest tkRequest = new TokenRequest();
		tkRequest.setClient_id("655c5cb4-906b-4b90-a66b-cdbbcb246717");
		tkRequest.setClient_secret("3OPxYLDsdBOwC/2xFQv2gg7kcg/1h1hEsJqhmKFgR9Q=");
		tkRequest.setGrant_type("client_credentials");
		ResponseEntity<Token> response = proxy.getToken(tkRequest);
		return response.getBody();
	}

	public ResponseEntity<DoctorDTO> insertDoctor(DoctorDTO doctor) {
		Token token = getToken();
		ResponseEntity<DoctorDTO> response = proxy.insertDoctor(doctor, token.getAccess_token());
		return ResponseEntity.ok(response.getBody());
	}
}
