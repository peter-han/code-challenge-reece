package com.phan.codechallenge.reece.repository.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Builder
@Data
@Table
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 10)
    private Integer phone;

    @ManyToOne
    @JoinColumn(name = "address_book_id", nullable = false)
    private AddressBook addressBook;
}
