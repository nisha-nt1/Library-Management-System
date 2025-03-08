package com.liberary.repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liberary.Entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);

}
