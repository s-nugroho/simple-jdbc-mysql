package com.testing.jdbc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Author {

    @Id
    private Long id;
}
