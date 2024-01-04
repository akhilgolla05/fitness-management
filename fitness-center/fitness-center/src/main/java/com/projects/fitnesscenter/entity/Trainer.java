package com.projects.fitnesscenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.ArrayList;
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

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience")
    private String experience;

    @JsonIgnore
    @OneToMany(mappedBy = "trainer",fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH
    })

    private List<Member> members;


    public void addMember(Member member){
        if(members == null){
            members = new ArrayList<>();
        }
        members.add(member);
        member.setTrainer(this);
    }

}
