package br.com.sd.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.sd.auth.Token;
import br.com.sd.auth.TokenRequest;
import io.swagger.v3.oas.annotations.Operation;

@FeignClient(name = "sdApiIntegration", url = "https://beta.sdconecta.com")
public interface DoctorProxy {
	
	@Operation(summary = "Post for receiving a token")
	@PostMapping(path = "/oauth2/token")
	ResponseEntity<Token> insertTokenRequest(@RequestBody Token tkRequest);
}