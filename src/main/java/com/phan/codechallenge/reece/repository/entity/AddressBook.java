package com.phan.codechallenge.reece.repository.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder
@Data
@Table(name = "address_book")
public class AddressBook {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @Column(name = "create_timestamp", insertable = false, updatable = false)
    private Timestamp createTimestamp;

    @Column(name = "create_timestamp", insertable = false, updatable = false)
    private Timestamp updateTimestamp;
}
