package br.com.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sd.model.Doctor;
import br.com.sd.repository.DoctorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Doctor Endpoint")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorRepository repository;
	
	@GetMapping(value = "/{id}")
	public Doctor doctor(@PathVariable("id") Long id) {
		var doc = repository.getById(id);
		return doc;
	}
	
}
