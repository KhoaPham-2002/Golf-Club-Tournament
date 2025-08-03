package com.keyin.golfclub_api.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private double entryFee;
    private double cashPrizeAmount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "member_tournament",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    @JsonIgnoreProperties("tournaments")
    private Set<Member> members = new HashSet<>();


    public Tournament() {
    }

    public Tournament(LocalDate startDate, LocalDate endDate, String location, double entryFee, double cashPrizeAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrizeAmount = cashPrizeAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public double getCashPrizeAmount() {
        return cashPrizeAmount;
    }

    public void setCashPrizeAmount(double cashPrizeAmount) {
        this.cashPrizeAmount = cashPrizeAmount;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        this.members.add(member);
        member.getTournaments().add(this); 
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id + '\'' +
                ", startDate=" + startDate + '\'' +
                ", endDate=" + endDate + '\'' +
                ", location='" + location + '\'' +
                ", entryFee=" + entryFee + '\'' +
                ", cashPrizeAmount=" + cashPrizeAmount + '\'' +
                ", totalMembers=" + members.size() +
                '}';
    }
}
