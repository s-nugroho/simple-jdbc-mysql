package com.testing.jdbc.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements EntityAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    private String author;

    @Column(name = "release_date")
    private Long releaseDate;
}
