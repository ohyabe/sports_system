package com.example.login.service.ifs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.entity.EventSchedule;
import com.example.login.repository.EventScheduleRepository;



@Service
public class EventScheduleService {

    @Autowired
    private EventScheduleRepository eventScheduleRepository;

    public List<EventSchedule> findAll() {
        return eventScheduleRepository.findAll();
    }

    public Optional<EventSchedule> findById(Long id) {
        return eventScheduleRepository.findById(id);
    }

    public EventSchedule save(EventSchedule eventSchedule) {
        return eventScheduleRepository.save(eventSchedule);
    }

    public void deleteById(Long id) {
        eventScheduleRepository.deleteById(id);
    }
}
