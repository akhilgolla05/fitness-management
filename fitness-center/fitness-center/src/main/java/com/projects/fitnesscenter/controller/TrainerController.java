package com.projects.fitnesscenter.controller;

import com.projects.fitnesscenter.entity.Member;
import com.projects.fitnesscenter.entity.Trainer;
import com.projects.fitnesscenter.exception.TrainerNotFoundException;
import com.projects.fitnesscenter.response.MemberResponse;
import com.projects.fitnesscenter.response.TrainerResponse;
import com.projects.fitnesscenter.service.TrainerService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/trainers")

public class TrainerController {

    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/trainer/add")
    public ResponseEntity<TrainerResponse> addTrainer(@RequestBody Trainer trainer){

        Trainer theTrainer = trainerService.addTrainer(trainer);

        TrainerResponse trainerResponse = new TrainerResponse(
                trainer.getTrainerId(),trainer.getFirstName()
        ,trainer.getLastName(),trainer.getEmail(),trainer.getSpecialization(),
                trainer.getExperience(),trainer.getMembers());

        return ResponseEntity.ok(trainerResponse);

    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<TrainerResponse> getTrainerById(@PathVariable("trainerId") int trainerId){

        Optional<Trainer> trainer = trainerService.getTrainer(trainerId);

//        List<Member> displayMembers = new ArrayList<>();
//        var members=  trainer.get().getMembers();
//        for(Member member: members){
//            var theMember= new Member(member.getMembershipId(),
//                    member.getFirstName(),member.getLastName(),member.getEmail());
//                    displayMembers.add(theMember);
//        }


        var trainerResponse = new TrainerResponse(
                trainer.get().getTrainerId(),trainer.get().getFirstName(),
                trainer.get().getLastName(), trainer.get().getEmail(),
                trainer.get().getSpecialization(), trainer.get().getExperience(),trainer.get().getMembers()
        );


//        public EntityModel<User> retrieveUser(@PathVariable int id) {
//            User user = service.findOne(id);
//
//            if(user==null)
//                throw new UserNotFoundException("id:"+id);
//
//            EntityModel<User> entityModel = EntityModel.of(user);
//
//            WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
//            entityModel.add(link.withRel("all-users"));
//
//            return entityModel;}


        return ResponseEntity.ok(trainerResponse);

    }

    @GetMapping("/all-trainers")
    public ResponseEntity<List<TrainerResponse>> getTrainerById(){



       List<Trainer> trainers = trainerService.getAllTrainers();
       List<TrainerResponse> trainerResponses = new ArrayList<>();
       for(Trainer trainer: trainers){
           var trainerResponse = new TrainerResponse(
                   trainer.getTrainerId(),trainer.getFirstName(),trainer.getLastName(),
                   trainer.getEmail(), trainer.getSpecialization(), trainer.getExperience(),trainer.getMembers());
           trainerResponses.add(trainerResponse);

       }

        return ResponseEntity.ok(trainerResponses);

    }

    //retrieve all trainers with Entity Model
//    @GetMapping("/all-trainers-heatos")
//    public EntityModel<Trainer> retrieveAllTrainersUsingHeatos() {
//
//            List<Trainer> trainers = trainerService.getAllTrainers();
//
//            if(trainers==null)
//                throw new TrainerNotFoundException("Np Trainers Found");
//
//            EntityModel<List<Trainer>> entityModel = EntityModel.of(trainers);
//
//            WebMvcLinkBuilder link =  linkTo(methodOn(Member.getClass()).retrieveAllUsers());
//            entityModel.add(link.withRel("all-users"));
//
//            return entityModel;}


    @DeleteMapping("/trainer/delete/{trainerId}")
    public ResponseEntity<?> deleteTrainerById(@PathVariable("trainerId") int trainerId){

        trainerService.deleteTrainer(trainerId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/trainer/update/{trainerId}")
    public ResponseEntity<TrainerResponse> updateTrainerById(@PathVariable("trainerId") int trainerId,
                                                             @RequestBody Trainer trainer){

       Trainer theTrainer =  trainerService.updateTrainer(trainerId, trainer);

       TrainerResponse response = new TrainerResponse(
               theTrainer.getTrainerId(), theTrainer.getEmail(),
               theTrainer.getFirstName(), theTrainer.getLastName(),
               theTrainer.getExperience(), theTrainer.getSpecialization(),trainer.getMembers());

       return ResponseEntity.ok(response);
    }


}




