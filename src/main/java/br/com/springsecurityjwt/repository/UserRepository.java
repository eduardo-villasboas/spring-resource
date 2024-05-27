package br.com.springsecurityjwt.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.springsecurityjwt.model.TBUser;

public interface UserRepository extends CrudRepository<TBUser, String> {
  Optional<TBUser> findByUsername(String username);
}
