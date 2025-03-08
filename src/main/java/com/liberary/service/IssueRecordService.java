package com.liberary.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liberary.Entity.Book;
import com.liberary.Entity.IssueRecord;
import com.liberary.Entity.User;
import com.liberary.repository.BookRepo;
import com.liberary.repository.IssueRecordRepo;
import com.liberary.repository.UserRepo;

@Service
public class IssueRecordService {
	
	@Autowired
	private IssueRecordRepo issueRecordRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	public IssueRecord add(IssueRecord issueRecord) {
		return issueRecordRepo.save(issueRecord);
	}
	
	
	public IssueRecord issueTheBook(Long bookId) {
		Book book = bookRepo.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found"));
		
		if(book.getQuantity()<=0 || !book.getIsAvailable()) {
			throw new RuntimeException("Book is not available");
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
		
		IssueRecord issueRecord = new IssueRecord();
		issueRecord.setIssueDate(LocalDate.now());
		issueRecord.setDueDate(LocalDate.now().plusDays(14));
		issueRecord.setIsReturned(false);
		issueRecord.setUser(user);
		issueRecord.setBook(book);
		
		book.setQuantity(book.getQuantity()-1);
		if(book.getQuantity()==0) {
			book.setIsAvailable(false);
		}
		bookRepo.save(book);
		return issueRecordRepo.save(issueRecord);
	}
	
	public IssueRecord returnTheBook(Long issueRecordId) {
		IssueRecord issueRecord = issueRecordRepo.findById(issueRecordId).orElseThrow(()->
		new RuntimeException("Issue Record Not found"));
		
		if(issueRecord.getIsReturned()) {
			throw new RuntimeException("Book is already returned");
		}
		
		Book book = issueRecord.getBook();
		book.setQuantity(book.getQuantity()+1);
		book.setIsAvailable(true);
		bookRepo.save(book);
		
		issueRecord.setReturnDate(LocalDate.now());
		issueRecord.setIsReturned(true);
		
		return issueRecordRepo.save(issueRecord);
	}

}
