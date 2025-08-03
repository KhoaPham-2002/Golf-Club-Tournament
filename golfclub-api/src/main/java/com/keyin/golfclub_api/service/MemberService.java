package com.keyin.golfclub_api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyin.golfclub_api.model.Member;
import com.keyin.golfclub_api.model.Tournament;
import com.keyin.golfclub_api.repository.MemberRepository;
import com.keyin.golfclub_api.repository.TournamentRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Member> searchByPhone(String phone) {
        return memberRepository.findByPhoneContaining(phone);
    }

    public List<Member> searchByStartDate(LocalDate startDate) {
        return memberRepository.findByStartDate(startDate);
    }

    public List<Member> searchByDuration(int duration) {
        return memberRepository.findByDuration(duration);
    }

    public Member addMemberToTournament(Long memberId, Long tournamentId) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(tournamentId);

        if (memberOpt.isPresent() && tournamentOpt.isPresent()) {
            Member member = memberOpt.get();
            Tournament tournament = tournamentOpt.get();

            member.getTournaments().add(tournament);
            tournament.getMembers().add(member);

            tournamentRepository.save(tournament);
            return memberRepository.save(member);
        } else {
            throw new RuntimeException("Member or Tournament not found");
        }
    }
}


