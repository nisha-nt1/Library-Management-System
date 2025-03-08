package com.liberary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liberary.Entity.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
	

}
