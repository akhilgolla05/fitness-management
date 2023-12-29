package com.projects.fitnesscenter.service;

import com.projects.fitnesscenter.entity.Trainer;
import com.projects.fitnesscenter.exception.TrainerNotFoundException;
import com.projects.fitnesscenter.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service

public class TrainerServiceImpl implements TrainerService{

    private TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer addTrainer(String firstName, String lastName, String email, MultipartFile photo, String specialization, String experience) throws IOException, SQLException {

        Trainer trainer = new Trainer();
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setEmail(email);
        trainer.setSpecialization(specialization);
        trainer.setExperience(experience);
        if(!photo.isEmpty()){

            byte[] photoBytes = photo.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            trainer.setPhoto(photoBlob);
        }

        return trainerRepository.save(trainer);
    }

    @Override
    public byte[] getTrainerPhotoById(long trainerId) throws SQLException {
        Optional<Trainer> trainer = trainerRepository.findById(trainerId);
        if(trainer.isEmpty()){
            throw new TrainerNotFoundException("Sorry, Trainer Not Found!");
        }
        Blob photoBlob = trainer.get().getPhoto();
        if(photoBlob != null){
            return photoBlob.getBytes(1L, (int) photoBlob.length());
        }
        return new byte[0];
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Optional<Trainer> getTrainerById(int trainerId) {
        return trainerRepository.findById((long) trainerId);
    }

    @Override
    public void deleteTrainer(int trainerId) {

        Optional<Trainer> trainer = trainerRepository.findById((long) trainerId);
        if(trainer.isEmpty()){
            throw new TrainerNotFoundException("Sorry, Trainer with id : "+trainerId+" Not Found");
        }
        trainerRepository.deleteById((long) trainerId);


    }
}
