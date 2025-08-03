package com.keyin.golfclub_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyin.golfclub_api.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByPhoneContaining(String phone);
    List<Member> findByStartDate(LocalDate startDate);
    List<Member> findByDuration(int duration);
}

