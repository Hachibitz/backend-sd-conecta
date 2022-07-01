package br.com.sd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sd.model.Doctor;
import br.com.sd.model.DoctorDTORequest;
import br.com.sd.model.DoctorDTOResponse;
import br.com.sd.repository.DoctorRepository;
import br.com.sd.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Doctor Endpoint")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService service;
	
	@Autowired
	private DoctorRepository repository;
    
	@Operation(summary = "Send the Doctor to create in the body with the bearerToken in the headers.")
	@RequestMapping(value = "c/oauth2", method = RequestMethod.POST)
	public ResponseEntity<DoctorDTOResponse> doc(@RequestBody DoctorDTORequest doctor) {
		return service.insertDoctor(doctor);
	}
	
	@Operation(summary = "Update a doctor by his id and send the doctor with the modifications in the body.")
	@PutMapping(path = "/u/{id}")
	public String updateDoc(@PathVariable("id") Long id, @RequestBody Doctor doctor){
		return service.updateDoctor(id, doctor);
	}
	
	@Operation(summary = "Delete a doctor by his id.")
	@GetMapping(value = "/d/{id}")
	public String deleteDoc(@PathVariable("id") Long id) {
		return service.deleteDoctor(id);
	}
	
	@Operation(summary = "Find a doctor by his id.")
	@GetMapping(value = "/find/{id}")
	public Doctor findDoctor(@PathVariable("id") Long id) {
		return service.findDoctorId(id);
	}
	
	@Operation(summary = "List all doctors. Filter by name and specialty optionally.")
	@GetMapping(value = "/list")
	public List<Doctor> listDoc(@RequestParam(required = false) String name, @RequestParam(required = false) String specialty){
		return service.getDoctors(name, specialty);
	}
	
	@Operation(summary = "Login page.")
	@PostMapping(path = "/login/{email}/{senha}")
	public ResponseEntity<DoctorDTOResponse> doct(@PathVariable("email") String email, @PathVariable("senha") String senha){
		return service.login(email, senha);
	}
	
	@GetMapping(path = "/flywayschema")
	public List<String> flywaySchema(){
		return repository.getFlywaySchema();
	}
		
}