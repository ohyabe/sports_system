package com.example.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.IndividualPerformance;
import com.example.login.service.ifs.PerformanceService;

@RestController
@RequestMapping("/score")
@CrossOrigin("http://localhost:5173")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/addIndividualPerformance")
    public String addIndividualPerformance(@RequestBody IndividualPerformance individualPerformance) {
        performanceService.addPerformance(individualPerformance);
        return "Individual Performance Added Successfully.";
    }

    @GetMapping("/individualPerformance/{id}")
    public IndividualPerformance getIndividualPerformanceById(@PathVariable("id") int id) {
        return performanceService.getIndividualPerformanceById(id);
    }

    @GetMapping("/individualPerformances")
    public List<IndividualPerformance> getIndividualPerformances() {
        return performanceService.getIndividualPerformance();
    }

    @DeleteMapping("/individualPerformance/{id}")
    public String deleteIndividualPerformance(@PathVariable("id") int id) {
        performanceService.deleteIndividualPerformance(id);
        return "Individual Performance Deleted Successfully.";
    }

    @GetMapping("/individualPerformances/item/{itemId}")
    public List<IndividualPerformance> getIndividualPerformancesByItemId(@PathVariable("itemId") int itemId) {
        return performanceService.getIndividualPerformanceByItemId(itemId);
    }

    @PutMapping("/individualPerformances/item/{itemId}")
    public List<IndividualPerformance> updateIndividualPerformancesByItemId(@PathVariable("itemId") int itemId, @RequestBody IndividualPerformance updatedData) {
        return performanceService.updateIndividualPerformancesByItemId(itemId, updatedData);
    }

    @PutMapping("/individualPerformance/{itemId}/{id}")
    public IndividualPerformance updateIndividualPerformanceByIdAndItemId(
            @PathVariable("itemId") int itemId, 
            @PathVariable("id") int id, 
            @RequestBody IndividualPerformance updatedData) {
        return performanceService.updateIndividualPerformanceByIdAndItemId(id, itemId, updatedData);
    }

    @PutMapping("/individualPerformances/batch")
    public ResponseEntity<List<IndividualPerformance>> updateIndividualPerformancesBatch(@RequestBody List<IndividualPerformance> performances) {
        List<IndividualPerformance> updatedPerformances = performanceService.updateIndividualPerformancesBatch(performances);
        return ResponseEntity.ok(updatedPerformances);
    }

    @GetMapping("/individualPerformances/classes/{classes}")
    public List<IndividualPerformance> getIndividualPerformancesByClasses(@PathVariable("classes") String classes) {
        return performanceService.getIndividualPerformanceByClasses(classes);
    }

    @GetMapping("/individualPerformances/breakRecord")
    public ResponseEntity<List<IndividualPerformance>> getPerformancesWithBreakRecord() {
        List<IndividualPerformance> performances = performanceService.getIndividualPerformancesWithBreakRecord();
        return ResponseEntity.ok(performances);
    }
}
