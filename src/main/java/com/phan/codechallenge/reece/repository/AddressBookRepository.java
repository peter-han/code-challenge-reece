package com.phan.codechallenge.reece.repository;

import com.phan.codechallenge.reece.model.AddressBook;
import com.phan.codechallenge.reece.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, String> {

    AddressBook saveByName(String name);

    /**
     * find address book by name;
     * <p>
     * if cannot find exist entity then a new Address Book will be returned.
     *
     * @param name
     * @return never be null
     */
    AddressBook findByName(String name);

    /**
     * find all
     *
     * @return
     */
    Collection<AddressBook> getAll();

    /**
     * contact could be duplicated in multiple address books.
     *
     * @param contact
     * @return
     */
    Set<AddressBook> getAddressBookByContact(Contact contact);
}
