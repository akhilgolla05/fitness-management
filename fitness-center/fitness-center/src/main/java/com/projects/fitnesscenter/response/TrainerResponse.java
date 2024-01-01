package com.projects.fitnesscenter.response;


import com.projects.fitnesscenter.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TrainerResponse {

    private int TrainerId;

    private String firstName;

    private String lastName;

    private String email;

    private String specialization;

    private String experience;

    private List<Member> members;


//    public TrainerResponse(int trainerId, String firstName, String lastName, String email, String specialization, String experience) {
//        TrainerId = trainerId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.specialization = specialization;
//        this.experience = experience;
//    }


    public TrainerResponse(int trainerId, String firstName, String lastName, String email,
                           String specialization, String experience, List<Member> members) {
        TrainerId = trainerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialization = specialization;
        this.experience = experience;
        this.members = members;
    }
}
