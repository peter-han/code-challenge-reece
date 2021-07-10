package com.phan.codechallenge.reece.repository;

import com.phan.codechallenge.reece.repository.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    @Query("SELECT a FROM AddressBook a WHERE a.user.name = ?1 AND a.name= ?2")
    Optional<AddressBook> findByUserAndName(String userName, String name);
}
