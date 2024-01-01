package com.projects.fitnesscenter.repository;

import com.projects.fitnesscenter.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
