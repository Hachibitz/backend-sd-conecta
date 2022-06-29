package br.com.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sd.auth.Token;
import br.com.sd.auth.TokenRequest;
import br.com.sd.model.DoctorDTORequest;
import br.com.sd.model.DoctorDTOResponse;
import br.com.sd.proxy.DoctorProxy;

@Service
public class DoctorService {
	
	@Autowired
	DoctorProxy proxy;
	
	@Autowired
	RestTemplate restTemplate;
	
	public Token getToken() {
		TokenRequest tkRequest = new TokenRequest();
		tkRequest.setClient_id("655c5cb4-906b-4b90-a66b-cdbbcb246717");
		tkRequest.setClient_secret("3OPxYLDsdBOwC/2xFQv2gg7kcg/1h1hEsJqhmKFgR9Q=");
		tkRequest.setGrant_type("client_credentials");
		ResponseEntity<Token> response = proxy.getToken(tkRequest);
		return response.getBody();
	}

	/*public ResponseEntity<DoctorDTO> insertDoctor(DoctorDTO doctor) {
		Token token = getToken();
		System.out.println(token.getAccess_token());
		ResponseEntity<DoctorDTO> response = proxy.insertDoctor(doctor, "Bearer "+token.getAccess_token());
		return ResponseEntity.ok(response.getBody());
	}*/
	
	public ResponseEntity<DoctorDTOResponse> insertDoctor(DoctorDTORequest doctor){
		Token token = getToken();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBearerAuth(token.getAccess_token());
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DoctorDTORequest> request = 
			      new HttpEntity<>(doctor, httpHeaders);
		ResponseEntity<DoctorDTOResponse> response = restTemplate
				.exchange("https://beta.sdconecta.com/api/v2/partners/generate-user-token",
						HttpMethod.POST, request, DoctorDTOResponse.class);
		return response;
	}
}
