package br.com.springsecurityjwt.web;

import br.com.springsecurityjwt.security.Token;
import br.com.springsecurityjwt.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("authenticate")
  public Token authenticate(
      Authentication authentication) {
    return authenticationService.authenticate(authentication);
  }
}
