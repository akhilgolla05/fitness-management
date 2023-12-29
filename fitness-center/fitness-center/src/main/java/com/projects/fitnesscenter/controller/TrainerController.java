package com.projects.fitnesscenter.controller;

import com.projects.fitnesscenter.entity.Trainer;
import com.projects.fitnesscenter.exception.TrainerNotFoundException;
import com.projects.fitnesscenter.response.TrainerResponse;
import com.projects.fitnesscenter.service.TrainerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainers")
//@RequiredArgsConstructor
public class TrainerController {


    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/add-trainer")
    public ResponseEntity<TrainerResponse> addTrainer(
            @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("email") String email, @RequestParam("photo") MultipartFile photo,
            @RequestParam("specialization") String specialization, @RequestParam("experience") String experience
    ) throws SQLException, IOException {

        Trainer theTrainer = trainerService.addTrainer(firstName, lastName, email, photo, specialization, experience);

        TrainerResponse response =
                new TrainerResponse((int) theTrainer.getTrainerId(),
                        theTrainer.getFirstName(),
                        theTrainer.getLastName(),
                        theTrainer.getEmail(),
                        theTrainer.getSpecialization(),
                        theTrainer.getExperience());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/all")
    public ResponseEntity<List<TrainerResponse>> getAllTrainers() throws SQLException {

        List<Trainer> trainers = trainerService.getAllTrainers();
        List<TrainerResponse> responses = new ArrayList<>();
        for (Trainer trainer : trainers) {
            byte[] photoBytes = trainerService.getTrainerPhotoById(trainer.getTrainerId());
            if (photoBytes != null && photoBytes.length > 0) {

                String base64String = Base64.encodeBase64String(photoBytes);
                TrainerResponse trainerResponse = new TrainerResponse();
                BeanUtils.copyProperties(trainer, trainerResponse);
                trainerResponse.setPhoto(base64String);
                responses.add(trainerResponse);

            }
        }
        return ResponseEntity.ok(responses);


    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<Optional<TrainerResponse>> getTrainerById(@PathVariable("trainerId") int trainerId)
            throws SQLException {

        Optional<Trainer> trainer = trainerService.getTrainerById(trainerId);
        System.out.println(trainer.get().getTrainerId());
        if (trainer.isEmpty()) {
            throw new TrainerNotFoundException("Sorry, Trainer with id : " + trainerId + " Not Found");
        } else {

            TrainerResponse trainerResponse = new TrainerResponse();

            if(trainer.get().getPhoto() != null){
                byte[] photoBytes = trainerService.getTrainerPhotoById(trainer.get().getTrainerId());
                String base64Bytes = Base64.encodeBase64String(photoBytes);
                BeanUtils.copyProperties(trainer.get(), trainerResponse);
                trainerResponse.setPhoto(base64Bytes);
                return ResponseEntity.ok(Optional.of(trainerResponse));

            }
            BeanUtils.copyProperties(trainer.get(), trainerResponse);

            return ResponseEntity.ok(Optional.of(trainerResponse));

        }

    }


    @DeleteMapping("/trainer/delete/{trainerId}")
    public ResponseEntity<?> deleteTrainerById(@PathVariable("trainerId") int trainerId){
        trainerService.deleteTrainer(trainerId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
