package com.liberary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liberary.Entity.Book;
import com.liberary.dto.BookDto;
import com.liberary.repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	BookRepo bookRepo;
	
	public Book saveBook(BookDto bookDto) {
		Book book = new Book();
		book.setTitle(bookDto.getTitle());
		book.setAuthor(bookDto.getAuthor());
		book.setIsbn(bookDto.getIsbn());
		book.setIsAvailable(bookDto.getIsAvailable());
		book.setQuantity(bookDto.getQuantity());
		
		return bookRepo.save(book);
	}
	
	public List<Book> getAllBooks(){
		return bookRepo.findAll();
	}

	public Book getBookById(Long id) {
		Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
		return book;
	}
	
	public void deleteBook(Long id) {
		 bookRepo.deleteById(id);
	}

	public Book updateBook(Long id, BookDto bookDto) {
		Book oldBook = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
		
		oldBook.setTitle(bookDto.getTitle());
		oldBook.setAuthor(bookDto.getAuthor());
		oldBook.setIsbn(bookDto.getIsbn());
		oldBook.setIsAvailable(bookDto.getIsAvailable());
		oldBook.setQuantity(bookDto.getQuantity());
		
		return bookRepo.save(oldBook);
	}
	
}
