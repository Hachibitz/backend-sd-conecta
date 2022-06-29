package br.com.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sd.model.DoctorDTORequest;
import br.com.sd.model.DoctorDTOResponse;
import br.com.sd.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Doctor Endpoint")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService service;
    
	@RequestMapping(value = "/auth2", method = RequestMethod.POST)
	public ResponseEntity<DoctorDTOResponse> doc(@RequestBody DoctorDTORequest doctor) {
		return service.insertDoctor(doctor);
	}
}