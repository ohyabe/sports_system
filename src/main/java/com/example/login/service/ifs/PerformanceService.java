package com.example.login.service.ifs;

import java.util.List;

import com.example.login.entity.IndividualPerformance;

public interface PerformanceService {
	


    // Individual Performance Methods
    IndividualPerformance addPerformance(IndividualPerformance individualPerformance);

    IndividualPerformance getIndividualPerformanceById(int id);

    List<IndividualPerformance> getIndividualPerformance();

    void deleteIndividualPerformance(int id);

    IndividualPerformance updateIndividualPerformance(IndividualPerformance individualPerformance);
    
    // 
    List<IndividualPerformance> getIndividualPerformanceByItemId(int itemId);

    List<IndividualPerformance> updateIndividualPerformancesByItemId(int itemId, IndividualPerformance updatedData);

    IndividualPerformance updateIndividualPerformanceByIdAndItemId(int id, int itemId, IndividualPerformance updatedData);

    List<IndividualPerformance> updateIndividualPerformancesBatch(List<IndividualPerformance> performances);
    
    List<IndividualPerformance> getIndividualPerformanceByClasses(String classes);
    
    List<IndividualPerformance> getIndividualPerformancesWithBreakRecord();
    //
   
}
