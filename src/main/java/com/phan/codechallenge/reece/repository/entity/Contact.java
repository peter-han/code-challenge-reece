package com.phan.codechallenge.reece.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Contact {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 10)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "address_book_id", nullable = false)
    private AddressBook addressBook;
}
