package br.com.springsecurityjwt.resource.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<TBUser, String> {
  Optional<TBUser> findByUsername(String username);
}
