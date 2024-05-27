package br.com.springsecurityjwt.security;

public class Token {

    private final String token;

    Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
