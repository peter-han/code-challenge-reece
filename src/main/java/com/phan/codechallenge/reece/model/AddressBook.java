package com.phan.codechallenge.reece.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class AddressBook {
    String id;
    String name;
    List<Contact> contacts;
}
