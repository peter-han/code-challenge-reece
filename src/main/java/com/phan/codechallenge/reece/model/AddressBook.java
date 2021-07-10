package com.phan.codechallenge.reece.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class AddressBook {

    private String id;

    private String name;

    private List<Contact> contacts;
}
