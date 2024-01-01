package com.projects.fitnesscenter.response;

import com.projects.fitnesscenter.entity.Trainer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MemberResponse {

    private int membershipId;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private LocalDate dateOfBirth;


    private LocalDate joiningDate;

    private int duration;

    private Trainer trainer;


    public MemberResponse(int membershipId, String firstName, String lastName, String email) {
        this.membershipId = membershipId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
//        this.trainer = trainer;
    }

    public MemberResponse(int membershipId, String firstName, String lastName, String email,
                          int age, LocalDate dateOfBirth, LocalDate joiningDate, int duration, Trainer trainer) {
        this.membershipId = membershipId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.dateOfBirth = dateOfBirth;

        this.joiningDate = joiningDate;
        this.duration = duration;
        this.trainer = trainer;
    }
}
