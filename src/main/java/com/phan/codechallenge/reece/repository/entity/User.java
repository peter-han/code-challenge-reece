package com.phan.codechallenge.reece.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AddressBook> addressBooks;

    @Column(name = "create_timestamp", insertable = false, updatable = false)
    private Timestamp createTimestamp;
    @Column(name = "create_timestamp", insertable = false, updatable = false)
    private Timestamp updateTimestamp;
}
