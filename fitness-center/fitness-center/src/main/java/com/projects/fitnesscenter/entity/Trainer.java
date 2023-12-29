package com.projects.fitnesscenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private int TrainerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience")
    private String experience;

    @OneToMany(mappedBy = "trainer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private List<Member> members;


}
