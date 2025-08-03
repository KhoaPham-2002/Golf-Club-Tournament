package com.keyin.golfclub_api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyin.golfclub_api.model.Member;
import com.keyin.golfclub_api.model.Tournament;
import com.keyin.golfclub_api.service.TournamentService;


@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.saveTournament(tournament);
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Optional<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament updatedTournament) {
        return tournamentService.updateTournament(id, updatedTournament);
    }

    @GetMapping("/search/by-start-date")
    public List<Tournament> searchByStartDate(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return tournamentService.searchByStartDate(startDate);
    }

    @GetMapping("/search/by-location")
    public List<Tournament> searchByLocation(@RequestParam String location) {
        return tournamentService.searchByLocation(location);
    }

    @GetMapping("/{id}/members")
    public List<Member> getMembersInTournament(@PathVariable Long id) {
        return tournamentService.getMembersInTournament(id);
    }

    @PostMapping("/{tournamentId}/assign/{memberId}")
    public ResponseEntity<Tournament> assignMemberToTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {

        Tournament updatedTournament = tournamentService.assignMemberToTournament(tournamentId, memberId);
        return ResponseEntity.ok(updatedTournament);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }
}
