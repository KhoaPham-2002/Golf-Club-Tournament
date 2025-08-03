package com.keyin.golfclub_api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyin.golfclub_api.model.Member;
import com.keyin.golfclub_api.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        if (member.getStartDate() == null || member.getDuration() <= 0) {
            throw new RuntimeException("Start date and duration must be provided.");
        }
        return memberService.saveMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Optional<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/search/by-name")
    public List<Member> searchByName(@RequestParam String name) {
        return memberService.searchByName(name);
    }

    @GetMapping("/search/by-phone")
    public List<Member> searchByPhone(@RequestParam String phone) {
        return memberService.searchByPhone(phone);
    }

    @GetMapping("/search/by-start-date")
    public List<Member> searchByStartDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return memberService.searchByStartDate(date);
    }

    @GetMapping("/search/by-duration")
    public List<Member> searchByDuration(@RequestParam int duration) {
        return memberService.searchByDuration(duration);
    }

    @PostMapping("/{memberId}/add-to-tournament/{tournamentId}")
    public Member addMemberToTournament(@PathVariable Long memberId, @PathVariable Long tournamentId) {
        return memberService.addMemberToTournament(memberId, tournamentId);
    }
}
