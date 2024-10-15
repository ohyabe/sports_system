package com.example.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.TournamentManagement;
import com.example.login.service.ifs.TournamentManagementService;

@RestController
@RequestMapping("/tournaments")
@CrossOrigin(origins = "http://localhost:5173")
public class TournamentManagementController {
    @Autowired
    private TournamentManagementService service;

    @GetMapping
    public List<TournamentManagement> getAllTournaments() {
        return service.getAllTournaments();
    }

    @PostMapping
    public TournamentManagement createTournament(@RequestBody TournamentManagement tournament) {
        return service.saveTournament(tournament);
    }

    @DeleteMapping
    public void deleteTournament(@RequestParam("id") Long id) {
        service.deleteTournament(id);
    }

    @PutMapping
    public TournamentManagement updateTournament(@RequestBody TournamentManagement tournament) {
        return service.updateTournament(tournament);
    }
}