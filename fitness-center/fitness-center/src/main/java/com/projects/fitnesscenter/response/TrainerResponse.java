package com.projects.fitnesscenter.response;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.Blob;


@Data

@NoArgsConstructor
public class TrainerResponse {

    private int TrainerId;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private String specialization;

    private String experience;

    public TrainerResponse(int trainerId, String firstName, String lastName, String email, String specialization, String experience) {
        TrainerId = trainerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialization = specialization;
        this.experience = experience;
    }

    public TrainerResponse(int trainerId, String firstName, String lastName, String email, byte[] photoBytes, String specialization, String experience) {
        TrainerId = trainerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo != null ? Base64.encodeBase64String(photoBytes) : null;
        this.specialization = specialization;
        this.experience = experience;
    }
}
