package com.phan.codechallenge.reece.repository;

import com.phan.codechallenge.reece.repository.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c WHERE c.addressBook = ?1 AND c.name = ?2")
    Optional<Contact> findByBookAndContactName(String addressBook, String contactName);
}
