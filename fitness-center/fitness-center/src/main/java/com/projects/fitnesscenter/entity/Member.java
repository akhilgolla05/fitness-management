package com.projects.fitnesscenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_id")
    private int membershipId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "photo")
    @Lob
    private Blob photo;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "duration")
    private int duration;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,
           CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer")
    private Trainer trainer;

}
