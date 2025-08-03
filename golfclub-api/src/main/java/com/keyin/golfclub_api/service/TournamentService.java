package com.keyin.golfclub_api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyin.golfclub_api.model.Member;
import com.keyin.golfclub_api.model.Tournament;
import com.keyin.golfclub_api.repository.MemberRepository;
import com.keyin.golfclub_api.repository.TournamentRepository;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> searchByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Member> getMembersInTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return new ArrayList<>(tournament.getMembers());
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public Tournament assignMemberToTournament(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new RuntimeException("Tournament not found with ID: " + tournamentId));
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));
        tournament.getMembers().add(member);
        
        return tournamentRepository.save(tournament);
    }

    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        return tournamentRepository.findById(id)
                .map(tournament -> {
                    tournament.setStartDate(updatedTournament.getStartDate());
                    tournament.setEndDate(updatedTournament.getEndDate());
                    tournament.setLocation(updatedTournament.getLocation());
                    tournament.setEntryFee(updatedTournament.getEntryFee());
                    tournament.setCashPrizeAmount(updatedTournament.getCashPrizeAmount());
                    return tournamentRepository.save(tournament);
                })
                .orElseGet(() -> {
                    updatedTournament.setId(id);
                    return tournamentRepository.save(updatedTournament);
                });
    }
}

