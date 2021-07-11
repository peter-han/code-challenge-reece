package com.phan.codechallenge.reece.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@Table(name = "address_book")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@With
public class AddressBook {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "user_name", nullable = false, length = 40)
    private String userName;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Contact> contacts;

//    @Column(name = "create_timestamp", insertable = false, updatable = false)
//    private Timestamp createTimestamp;
//
//    @Column(name = "create_timestamp", insertable = false, updatable = false)
//    private Timestamp updateTimestamp;
}
