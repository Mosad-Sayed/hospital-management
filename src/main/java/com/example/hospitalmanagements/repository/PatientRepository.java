package com.example.hospitalmanagements.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hospitalmanagements.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNameOfPatientContainingIgnoreCase(String name);
    List<Patient> findAllByOrderByAgeAsc();
    List<Patient> findByArchived(boolean archived, Sort sort);
    List<Patient> findByRegisteredByDepartmentAndArchived(String department, boolean archived);
    boolean existsByFileNoAndArchivedTrue(String fileNo);
    List<Patient> findByPatternFeedsIn(List<String> patternFeeds);
    @Query("SELECT p FROM Patient p WHERE "
            + "p.archived = false AND "
            + "(:patternFeeds = 'All' OR p.patternFeeds = :patternFeeds) AND "
            + "(:registeredByDepartment = 'All' OR p.registeredByDepartment = :registeredByDepartment) "
            + "ORDER BY (CASE WHEN CAST(p.room AS int) < 10 THEN 0 ELSE 1 END), CAST(p.room AS int) ASC")

    List<Patient> findByPatternFeedsAndRegisteredByDepartment(
            @Param("patternFeeds") String patternFeeds,
            @Param("registeredByDepartment") String registeredByDepartment);
    boolean existsByFileNo(String fileNo);
    Optional<Patient> findByFileNo(String fileNo);
	boolean existsByRoomAndBedAndCompanionNameAndRegisteredByDepartment(String room, String bed, String companionName,
			String registeredByDepartment);

    boolean existsByCompanionNameAndRegisteredByDepartment(String name, String department);

    
}



    
    
  
