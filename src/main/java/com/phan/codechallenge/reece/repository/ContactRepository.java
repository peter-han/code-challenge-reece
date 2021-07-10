package com.phan.codechallenge.reece.repository;

import com.phan.codechallenge.reece.repository.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
