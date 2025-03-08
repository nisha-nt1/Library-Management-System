package com.liberary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberary.Entity.IssueRecord;
import com.liberary.service.IssueRecordService;

@RestController
@RequestMapping("/issueRecords")
public class IssueRecordController {
	
	@Autowired
	private IssueRecordService issueRecordService;
	
	@PostMapping("/issueTheBook/{bookid}")
	public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long bookId){
		return ResponseEntity.ok(issueRecordService.issueTheBook(bookId));
	}
	
	@PostMapping("/returnthebook/{issuerecordid}")
	public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issueRecordId){
		return ResponseEntity.ok(issueRecordService.returnTheBook(issueRecordId));
	}

}
