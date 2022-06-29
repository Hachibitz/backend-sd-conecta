package br.com.sd.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.sd.auth.Token;
import br.com.sd.auth.TokenRequest;
import br.com.sd.model.DoctorDTO;
import feign.Headers;
import io.swagger.v3.oas.annotations.Operation;

@FeignClient(name = "sdApiIntegration", url = "https://beta.sdconecta.com")
public interface DoctorProxy {
	
	@Operation(summary = "Post for receiving a token")
	@PostMapping(path = "/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Headers("Content-Type: application/x-www-form-urlencoded")
	ResponseEntity<Token> getToken(@RequestBody TokenRequest tkRequest);
	
	@PostMapping(path = "/api/v2/partners/generate-user-token", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DoctorDTO> insertDoctor(@RequestBody DoctorDTO doctor, @RequestHeader(value = "Authorization") String bearerToken);
}