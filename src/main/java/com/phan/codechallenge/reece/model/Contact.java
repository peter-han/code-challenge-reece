package com.phan.codechallenge.reece.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Contact {
    String id;
    String name;
    String phone;
}
