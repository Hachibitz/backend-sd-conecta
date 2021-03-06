package br.com.sd.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.sd.auth.Token;
import br.com.sd.auth.TokenRequest;
import br.com.sd.model.DoctorDTORequest;
import br.com.sd.model.DoctorDTOResponse;
import feign.Headers;

@FeignClient(name = "sdApiIntegration", url = "https://beta.sdconecta.com")
public interface DoctorProxy {
	
	@PostMapping(path = "/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Headers("Content-Type: application/x-www-form-urlencoded")
	ResponseEntity<Token> getToken(@RequestBody TokenRequest tkRequest);
	
	@PostMapping(path = "/api/v2/partners/generate-user-token", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DoctorDTOResponse> insertDoctor(@RequestBody DoctorDTORequest doctor, @RequestHeader("Authorization") String bearerToken);
}