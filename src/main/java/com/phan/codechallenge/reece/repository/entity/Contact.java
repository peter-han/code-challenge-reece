package com.phan.codechallenge.reece.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 10)
    private Integer phone;

    @ManyToOne
    @JoinColumn(name = "address_book_id", nullable = false)
    private AddressBook addressBook;
}
