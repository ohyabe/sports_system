package com.example.login.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.EventSchedule;
import com.example.login.service.ifs.EventScheduleService;



@RestController
@RequestMapping("/event_schedule")
public class EventScheduleController {

    @Autowired
    private EventScheduleService eventScheduleService;

    @GetMapping
    public List<EventSchedule> getAllEventSchedules() {
        return eventScheduleService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<EventSchedule> getEventScheduleById(@PathVariable Long id) {
        return eventScheduleService.findById(id);
    }

    @PostMapping
    public EventSchedule createEventSchedule(@RequestBody EventSchedule eventSchedule) {
        return eventScheduleService.save(eventSchedule);
    }

    @PutMapping
    public EventSchedule updateEventSchedule(@RequestBody EventSchedule eventSchedule) {
        return eventScheduleService.save(eventSchedule);
    }

    @DeleteMapping
    public void deleteEventSchedule(@RequestParam Long id) {
        eventScheduleService.deleteById(id);
    }
}
