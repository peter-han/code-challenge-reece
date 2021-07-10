package com.phan.codechallenge.reece.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class User {
    String name;
    List<AddressBook> addressBooks;
}
