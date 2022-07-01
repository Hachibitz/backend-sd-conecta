package br.com.sd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sd.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	@Query(value = "select * from Doctor s where s.name = :name"
            , nativeQuery = true)
	List<Doctor> findByName(@Param("name") String name);
	
	@Query(value = "select * from Doctor s inner join CRM c on c.id = s.id where c.specialty = :specialty"
            , nativeQuery = true)
	List<Doctor> findBySpecialty(@Param("specialty") String specialty);
	
	@Query(value = "select * from Doctor s inner join CRM c on c.id = s.id where c.specialty = :specialty and s.name = :name"
            , nativeQuery = true)
	List<Doctor> findByNameAndSpecialty(@Param("specialty") String specialty, @Param("name") String name);
	
	@Query(value = "select * from Doctor s where s.email = :email"
            , nativeQuery = true)
	Doctor findByEmail(@Param("email") String email);
	
	@Query(value = "select * from Doctor.flyway_schema_history",
			 nativeQuery = true)
	List<String> getFlywaySchema();
	
}
