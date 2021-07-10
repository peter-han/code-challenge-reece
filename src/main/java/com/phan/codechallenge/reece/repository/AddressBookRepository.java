package com.phan.codechallenge.reece.repository;

import com.phan.codechallenge.reece.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, String> {
}
