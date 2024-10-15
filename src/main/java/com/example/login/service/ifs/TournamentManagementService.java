package com.example.login.service.ifs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.entity.TournamentManagement;
import com.example.login.repository.TournamentManagementRepository;




@Service
public class TournamentManagementService {
    @Autowired
    private TournamentManagementRepository repository;

    public List<TournamentManagement> getAllTournaments() {
        return repository.findAll();
    }

    public TournamentManagement saveTournament(TournamentManagement tournament) {
        return repository.save(tournament);
    }

    public void deleteTournament(Long id) {
        repository.deleteById(id);
    }

    public TournamentManagement updateTournament(TournamentManagement tournament) {
        if (repository.existsById(tournament.getId())) {
            return repository.save(tournament);
        } else {
            throw new RuntimeException("Tournament not found with id: " + tournament.getId());
        }
    }
}
