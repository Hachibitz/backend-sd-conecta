package br.com.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sd.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
