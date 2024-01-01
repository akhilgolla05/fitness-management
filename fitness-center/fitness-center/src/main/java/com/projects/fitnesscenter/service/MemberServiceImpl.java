package com.projects.fitnesscenter.service;


import com.projects.fitnesscenter.entity.Member;
import com.projects.fitnesscenter.exception.MemberNotFoundException;
import com.projects.fitnesscenter.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> getMember(int membershipId) {
        Optional<Member> member = memberRepository.findById(membershipId);
        if(member.isEmpty()){
            throw new MemberNotFoundException("Member With id: "+membershipId+ " Not Found!");
        }
        return member;
    }

    @Override
    public List<Member> getAllMembers() {

        List<Member> members = memberRepository.findAll();
        if(members.isEmpty()){
            throw new MemberNotFoundException("No Members Found!");
        }
        return members;
    }

    @Override
    public void deleteMember(int membershipId) {

        Optional<Member> member = memberRepository.findById(membershipId);

        if(member.isEmpty()){
            throw new MemberNotFoundException("Member With id: "+membershipId+ " Not Found!");
        }

        memberRepository.delete(member.get());
    }

    @Override
    public Member updateMember(int membershipId, Member member) {

        Optional<Member> theMember = memberRepository.findById(membershipId);

        if(theMember.isEmpty()){

            throw new MemberNotFoundException("Member With id: "+membershipId+ " Not Found!");
        }

        var updateMember = theMember.get();
        updateMember.setFirstName(member.getFirstName());
        updateMember.setLastName(member.getLastName());
        updateMember.setAge(member.getAge());
        updateMember.setDuration(member.getDuration());
        updateMember.setJoiningDate(member.getJoiningDate());
        updateMember.setDateOfBirth(member.getDateOfBirth());
        updateMember.setTrainer(member.getTrainer());


        return memberRepository.save(updateMember);
    }


}
