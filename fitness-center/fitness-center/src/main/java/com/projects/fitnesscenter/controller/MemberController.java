package com.projects.fitnesscenter.controller;


import com.projects.fitnesscenter.entity.Member;
import com.projects.fitnesscenter.entity.Trainer;
import com.projects.fitnesscenter.exception.TrainerNotFoundException;
import com.projects.fitnesscenter.response.MemberResponse;
import com.projects.fitnesscenter.response.TrainerResponse;
import com.projects.fitnesscenter.service.MemberService;
import com.projects.fitnesscenter.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TrainerService trainerService;


//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @PostMapping("/member/add")
    public ResponseEntity<Member> addMember(@RequestBody Member member){

        Member theMember = memberService.addMember(member);

//        MemberResponse memberResponse = new MemberResponse(
//                member.getMembershipId(),
//                member.getFirstName(), member.getLastName(), member.getEmail(),
//                member.getAge(),member.getDateOfBirth(),member.getJoiningDate(),
//                member.getDuration(),member.getTrainer());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{membershipId}")
                .buildAndExpand(theMember.getMembershipId())
                .toUri();
//
//        return ResponseEntity.created(location).build();

        return ResponseEntity.created(location).build();

    }

    //    @PostMapping("/member/add-trainer/{trainerId}/")

    @PostMapping("/member/{trainerId}/add")
    public ResponseEntity<?> addMemberWithTrainer(@PathVariable("trainerId") int trainerId,
                                                               @RequestBody Member member){

        try{
            Optional<Trainer> trainer = trainerService.getTrainer(trainerId);
            member.setTrainer(trainer.get());


            Member theMember = memberService.addMember(member);

            var theTrainer = new Trainer();
            theTrainer.addMember(theMember);

            MemberResponse memberResponse = new MemberResponse(
                    member.getMembershipId(),
                    member.getFirstName(), member.getLastName(), member.getEmail(),member.getAge()
                    ,member.getDateOfBirth(), member.getJoiningDate(),member.getDuration(),member.getTrainer());


            return ResponseEntity.ok(memberResponse);

        }catch(TrainerNotFoundException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/member/{membershipId}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable("membershipId") int membershipId){

        Optional<Member> member = memberService.getMember(membershipId);

        var memberResponse = new MemberResponse(
                member.get().getMembershipId(),
                member.get().getFirstName(), member.get().getLastName(), member.get().getEmail(),
                member.get().getAge(),member.get().getDateOfBirth(),member.get().getJoiningDate(),
                member.get().getDuration(),member.get().getTrainer());

        return ResponseEntity.ok(memberResponse);

    }

    @GetMapping("/all-members")
    public ResponseEntity<List<MemberResponse>> getAllMembers(){

        List<Member> members = memberService.getAllMembers();

//        for(Member member: members){
//            System.out.println(member.getJoiningDate());
//            System.out.println(member.getDateOfBirth());
//            System.out.println(member.getAge());
//            System.out.println(member.getDuration());
//        }

        List<MemberResponse> memberResponses = new ArrayList<>();
        for(Member member: members){
            var memberResponse = new MemberResponse(
                    member.getMembershipId(),member.getFirstName(),member.getLastName(),
                    member.getEmail(),member.getAge(),member.getDateOfBirth(),member.getJoiningDate()
            ,member.getDuration(),member.getTrainer());
            memberResponses.add(memberResponse);

        }

        return ResponseEntity.ok(memberResponses);

    }

    @DeleteMapping("/member/delete/{membershipId}")
    public ResponseEntity<?> deleteMemberById(@PathVariable("membershipId") int membershipId){

        memberService.deleteMember(membershipId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/member/update/{membershipId}")
    public ResponseEntity<MemberResponse> updateTrainerById(@PathVariable("membershipId") int membershipId,
                                                             @RequestBody Member member){

        Member theMember =  memberService.updateMember(membershipId, member);

        MemberResponse response = new MemberResponse(
                theMember.getMembershipId(),theMember.getFirstName(),theMember.getLastName(),
                theMember.getEmail(),member.getAge(),member.getDateOfBirth(),member.getJoiningDate(),
                member.getDuration(),member.getTrainer());

        return ResponseEntity.ok(response);
    }




}
