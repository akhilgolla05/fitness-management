package com.projects.fitnesscenter.service;


import com.projects.fitnesscenter.entity.Member;
import com.projects.fitnesscenter.entity.Trainer;
import com.projects.fitnesscenter.exception.TrainerNotFoundException;
import com.projects.fitnesscenter.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TrainerServiceImpl implements TrainerService{

    private TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Optional<Trainer> getTrainer(int trainerId) {

        Optional<Trainer> trainer = trainerRepository.findById((long) trainerId);
        if(trainer.isEmpty()){
            throw new TrainerNotFoundException("Trainer With id : "+trainerId+" Not Found!");
        }
        return trainer;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        if(trainers.isEmpty()){
            throw new TrainerNotFoundException("No Trainers Found!");
        }
        return trainers;
    }

    @Override
    public void deleteTrainer(int trainerId) {
        Optional<Trainer> trainer = trainerRepository.findById((long) trainerId);
        if(trainer.isEmpty()){
            throw new TrainerNotFoundException("Trainer With id : "+trainerId+" Not Found!");
        }
        var theTrainer = trainer.get();
        if(!theTrainer.getMembers().isEmpty()){
            List<Member> members = theTrainer.getMembers();
            for(Member member : members){
                member.setTrainer(null);
            }
        }
        trainerRepository.delete(trainer.get());

    }

    @Override
    public Trainer updateTrainer(int trainerId, Trainer trainer) {

        Optional<Trainer> theTrainer = trainerRepository.findById((long) trainerId);

        if(theTrainer.isEmpty()){
            throw new TrainerNotFoundException("Trainer With id : "+trainerId+" Not Found!");
        }

        var updateTrainer = theTrainer.get();
        updateTrainer.setExperience(trainer.getExperience());
        updateTrainer.setEmail(trainer.getEmail());
        updateTrainer.setSpecialization(trainer.getSpecialization());
        updateTrainer.setLastName(trainer.getLastName());
        updateTrainer.setFirstName(trainer.getFirstName());
        updateTrainer.setSpecialization(trainer.getSpecialization());

        return trainerRepository.save(updateTrainer);
    }
}
