package com.liberary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liberary.Entity.IssueRecord;

public interface IssueRecordRepo extends JpaRepository<IssueRecord, Long>{

}
