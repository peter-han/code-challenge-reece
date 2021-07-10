package com.phan.codechallenge.reece.repository.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AddressBook> addressBooks;

    @Column(name = "create_timestamp", insertable = false, updatable = false)
    private Timestamp createTimestamp;
    @Column(name = "create_timestamp", insertable = false, updatable = false)
    private Timestamp updateTimestamp;
}
