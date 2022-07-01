package br.com.sd.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sd.auth.Token;
import br.com.sd.auth.TokenRequest;
import br.com.sd.dtomapper.DoctorRequestMapper;
import br.com.sd.model.Doctor;
import br.com.sd.model.DoctorDTORequest;
import br.com.sd.model.DoctorDTOResponse;
import br.com.sd.proxy.DoctorProxy;
import br.com.sd.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	DoctorProxy proxy;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	DoctorRepository repository;
	
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
	
	private final DoctorRequestMapper doctorRequestMapper = new DoctorRequestMapper() {
		@Override
		public Doctor dtoToDoctorMapper(DoctorDTORequest dtoDoc){
			
			Doctor doctor = new Doctor();
			doctor.setEmail(dtoDoc.getEmail());
			doctor.setName(dtoDoc.getName());
			doctor.setSobrenome(dtoDoc.getSurname());
			doctor.setPhone(dtoDoc.getMobile_phone());
			doctor.setCRM(dtoDoc.getCrms());
			return doctor;
		}
	};
	
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
		Doctor doc = doctorRequestMapper.dtoToDoctorMapper(doctor);
		System.out.println(doc.getCRM());
		if(response.getStatusCode() == HttpStatus.OK) repository.
												save(doc);
		return response;
	}
	
	public String updateDoctor(Long id, Doctor theDoctor) {
		theDoctor.setId(id);
		repository.save(theDoctor);
		return "Update realizado!";
	}
	
	public String deleteDoctor(Long id) {
		Doctor theDoctor  = repository.getReferenceById(id);
		repository.delete(theDoctor);
		return "Delete realizado!";
	}
	
	public List<Doctor> getDoctors(String name, String crm) {
		if (Objects.nonNull(name) && !Objects.nonNull(crm)) {
			return repository.findByName(name);
		}
		if(Objects.nonNull(name) && Objects.nonNull(crm)) {
			return repository.findByNameAndSpecialty(crm, name);
		}
		if (!Objects.nonNull(name) && Objects.nonNull(crm)) {
			return repository.findBySpecialty(crm);
		}
		return repository.findAll();
	}
	
	public ResponseEntity<DoctorDTOResponse> login(String email, String senha){
		Doctor theDoctor = new Doctor();
		if(Objects.nonNull(repository.findByEmail(email))) {
			theDoctor = repository.findByEmail(email);
		}else{
			ResponseEntity<DoctorDTOResponse> resp = new ResponseEntity<DoctorDTOResponse>(HttpStatus.UNAUTHORIZED);
			return resp;
		}
		Token token = getToken();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBearerAuth(token.getAccess_token());
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Doctor> request = 
			      new HttpEntity<>(theDoctor, httpHeaders);
		ResponseEntity<DoctorDTOResponse> response = restTemplate
				.exchange("https://beta.sdconecta.com/api/v2/partners/generate-user-token",
						HttpMethod.POST, request, DoctorDTOResponse.class);
		theDoctor.setAuthorization_status(response.getBody().getAuthorization_status());
		if("AUTHORIZED".equals(response.getBody().getAuthorization_status())) {
			return response;
		}
		ResponseEntity<DoctorDTOResponse> resp = new ResponseEntity<DoctorDTOResponse>(HttpStatus.UNAUTHORIZED);
		return resp;
	}
}
