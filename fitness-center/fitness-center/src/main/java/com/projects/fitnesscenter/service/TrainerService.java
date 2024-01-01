package com.projects.fitnesscenter.service;


import com.projects.fitnesscenter.entity.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {


    Trainer addTrainer(Trainer trainer);

    Optional<Trainer> getTrainer(int trainerId);

    List<Trainer> getAllTrainers();

    void deleteTrainer(int trainerId);

    Trainer updateTrainer(int trainerId, Trainer trainer);
}
