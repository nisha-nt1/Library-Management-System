package com.liberary.dto;

import java.util.Set;

public class LoginResponseDto {
    
    private String token;
    private String username;
    private Set<String> roles;
    
    public LoginResponseDto() {
        super();
    }
    
    public LoginResponseDto(String token, String username, Set<String> roles) {
        super();
        this.token = token;
        this.username = username;
        this.roles = roles;
    }
    
    // Getters and setters...
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
    // --- Builder Implementation ---
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String token;
        private String username;
        private Set<String> roles;
        
        public Builder token(String token) {
            this.token = token;
            return this;
        }
        
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public Builder roles(Set<String> roles) {
            this.roles = roles;
            return this;
        }
        
        public LoginResponseDto build() {
            return new LoginResponseDto(token, username, roles);
        }
    }
}


	
	

