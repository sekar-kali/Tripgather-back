package org.wcs.tripgather.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private Long email;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean gender;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    private String country;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private Date birthDate;
}
