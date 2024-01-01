package com.projects.fitnesscenter.service;


import com.projects.fitnesscenter.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {


    Member addMember(Member member);

    Optional<Member> getMember(int membershipId);

    List<Member> getAllMembers();

    void deleteMember(int membershipId);

    Member updateMember(int membershipId, Member member);
}
