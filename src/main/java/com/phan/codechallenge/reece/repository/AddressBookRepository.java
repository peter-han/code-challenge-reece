package com.phan.codechallenge.reece.repository;

import com.phan.codechallenge.reece.repository.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    Optional<AddressBook> findByUserNameAndName(String owner, String name);
}
