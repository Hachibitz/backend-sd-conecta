package br.com.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sd.auth.Token;

public interface TokenRepository extends JpaRepository<Token, String> {

}
