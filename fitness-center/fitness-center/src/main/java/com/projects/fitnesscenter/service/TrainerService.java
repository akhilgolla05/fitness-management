package com.projects.fitnesscenter.service;

import com.projects.fitnesscenter.entity.Trainer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TrainerService {

    public Trainer addTrainer(String firstName, String lastName, String email, MultipartFile photo, String specialization, String experience) throws IOException, SQLException;

    byte[] getTrainerPhotoById(long trainerId) throws SQLException;

    List<Trainer> getAllTrainers();

    Optional<Trainer> getTrainerById(int trainerId);

    void deleteTrainer(int trainerId);
}
