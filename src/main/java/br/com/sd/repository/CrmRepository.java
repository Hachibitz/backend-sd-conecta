package br.com.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sd.model.Crm;

public interface CrmRepository extends JpaRepository<Crm, Long>{
	
}
