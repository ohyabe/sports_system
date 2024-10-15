package com.example.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.Participant;
import com.example.login.repository.ParticipantRepository;
import com.example.login.repository.TournamentManagementRepository;



@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private TournamentManagementRepository tournamentManagementRepository;

    @GetMapping
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @PostMapping
    public List<Participant> addParticipants(@RequestBody List<Participant> participants) {
        return participantRepository.saveAll(participants);
    }

    @GetMapping("/courseItems")
    public List<String> getCourseItems() {
        return tournamentManagementRepository.findAllCourseItems();
    }
}
