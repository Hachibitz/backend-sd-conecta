package br.com.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.sd.auth.Token;
import br.com.sd.model.Doctor;
import br.com.sd.model.DoctorDTO;
import br.com.sd.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Doctor Endpoint")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService service;
	
	private Token tk;
	
	RestTemplate restTemplate;
	
	/*@Operation(summary = "Get the doctors by the ID")
	@GetMapping(value = "/{id}")
	public Doctor doctor(@PathVariable("id") Long id) {
		var doc = repository.getById(id);
		return doc;
	}*/
	
	/*@Operation(summary = "Post for receiving a token")
    @RequestMapping(value = "/auth2", method = RequestMethod.POST)
    public String token(@RequestBody Token tk) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	HttpEntity<Token> entity = new HttpEntity<Token>(tk, headers);
    		return restTemplate.exchange(
    				"https://beta.sdconecta.com/oauth2/token", HttpMethod.POST, entity, String.class).getBody();
   }*/
	
	//@Operation(summary = "Post for receiving a token")
	/*@PostMapping("/auth/a1")
	public ResponseEntity<Token> insertToken(@RequestBody Token tkr) {
		tk = tokenRepository.save(tkr);
		System.out.println(tk.getAccess_token() +" "+ tk.getToken_type());
	    return ResponseEntity.status(HttpStatus.CREATED).build();
	}*/
    
	@RequestMapping(value = "/auth2", method = RequestMethod.POST)
	public ResponseEntity<DoctorDTO> doc(@RequestBody DoctorDTO doctor) {
		//TokenRequest tokenRequest = new TokenRequest();
		//tokenRequest.setClient_id("655c5cb4-906b-4b90-a66b-cdbbcb246717");
		//tokenRequest.setClient_secret("3OPxYLDsdBOwC/2xFQv2gg7kcg/1h1hEsJqhmKFgR9Q=");
		//tokenRequest.setGrant_type("client_credentials");
		return service.insertDoctor(doctor);
	}
}
