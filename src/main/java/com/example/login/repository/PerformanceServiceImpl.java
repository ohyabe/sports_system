package com.example.login.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.entity.IndividualPerformance;
import com.example.login.service.ifs.PerformanceService;

@Service("performanceServiceImplService")
public class PerformanceServiceImpl implements PerformanceService {

    

    @Autowired
    private IndividualRepository individualRepository;

   

    // Individual Performance Methods
    @Override
    public IndividualPerformance addPerformance(IndividualPerformance individualPerformance) {
        return individualRepository.save(individualPerformance);
    }

    @Override
    public IndividualPerformance getIndividualPerformanceById(int id) {
        return individualRepository.findById(id).orElse(null);
    }

    @Override
    public List<IndividualPerformance> getIndividualPerformance() {
        return individualRepository.findAll();
    }

    @Override
    public void deleteIndividualPerformance(int id) {
        individualRepository.deleteById(id);
    }

    @Override
    public IndividualPerformance updateIndividualPerformance(IndividualPerformance individualPerformance) {
        return individualRepository.save(individualPerformance);
    }
    
    //
    @Override
    public List<IndividualPerformance> getIndividualPerformanceByItemId(int itemId) {
        return individualRepository.findByItemId(itemId);
    }

    @Override
    public List<IndividualPerformance> updateIndividualPerformancesByItemId(int itemId, IndividualPerformance updatedData) {
        List<IndividualPerformance> performances = individualRepository.findByItemId(itemId);
        for (IndividualPerformance performance : performances) {
            performance.setAcademicYear(updatedData.getAcademicYear());
            performance.setHistoricalPerformance(updatedData.getHistoricalPerformance());
            performance.setCourseItem(updatedData.getCourseItem());
            performance.setPreMatch(updatedData.getPreMatch());
            performance.setClasses(updatedData.getClasses());
            performance.setStudentName(updatedData.getStudentName());
            performance.setProjectPerformance(updatedData.getProjectPerformance());
            performance.setBreakRecord(updatedData.getBreakRecord());
            performance.setRanking(updatedData.getRanking());
            performance.setProjectScore(updatedData.getProjectScore());
            performance.setTotalScore(updatedData.getTotalScore());
            // ��s��L�ݭn���r�q
            individualRepository.save(performance);
        }
        return performances;
    }

    @Override
    public IndividualPerformance updateIndividualPerformanceByIdAndItemId(int id, int itemId, IndividualPerformance updatedData) {
        IndividualPerformance performance = individualRepository.findByIdAndItemId(id, itemId);
        if (performance != null) {
            performance.setAcademicYear(updatedData.getAcademicYear());
            performance.setHistoricalPerformance(updatedData.getHistoricalPerformance());
            performance.setCourseItem(updatedData.getCourseItem());
            performance.setPreMatch(updatedData.getPreMatch());
            performance.setClasses(updatedData.getClasses());
            performance.setStudentName(updatedData.getStudentName());
            performance.setProjectPerformance(updatedData.getProjectPerformance());
            performance.setBreakRecord(updatedData.getBreakRecord());
            performance.setRanking(updatedData.getRanking());
            performance.setProjectScore(updatedData.getProjectScore());
            performance.setTotalScore(updatedData.getTotalScore());
            // ��s��L�ݭn���r�q
            return individualRepository.save(performance);
        }
        return null;
    }
    
    @Override
    public List<IndividualPerformance> updateIndividualPerformancesBatch(List<IndividualPerformance> performances) {
        for (IndividualPerformance performance : performances) {
            IndividualPerformance existingPerformance = individualRepository.findById(performance.getId()).orElse(null);
            if (existingPerformance != null) {
                existingPerformance.setAcademicYear(performance.getAcademicYear());
                existingPerformance.setHistoricalPerformance(performance.getHistoricalPerformance());
                existingPerformance.setCourseItem(performance.getCourseItem());
                existingPerformance.setPreMatch(performance.getPreMatch());
                existingPerformance.setClasses(performance.getClasses());
                existingPerformance.setStudentName(performance.getStudentName());
                existingPerformance.setProjectPerformance(performance.getProjectPerformance());
                existingPerformance.setBreakRecord(performance.getBreakRecord());
                existingPerformance.setRanking(performance.getRanking());
                existingPerformance.setProjectScore(performance.getProjectScore());
                existingPerformance.setTotalScore(performance.getTotalScore());
                individualRepository.save(existingPerformance);
            }
        }
        return individualRepository.saveAll(performances);
    }
    
    @Override
    public List<IndividualPerformance> getIndividualPerformanceByClasses(String classes) {
        return individualRepository.findByClasses(classes);
    }
    
    @Override
    public List<IndividualPerformance> getIndividualPerformancesWithBreakRecord() {
        return individualRepository.findByBreakRecord("true");
    }
    
    
   
}
