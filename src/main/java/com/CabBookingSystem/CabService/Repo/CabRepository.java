package com.CabBookingSystem.CabService.Repo;

import com.CabBookingSystem.CabService.DTO.CabDTO;
import com.CabBookingSystem.CabService.Entity.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabRepository extends JpaRepository<Cab,String> {

    @Query("SELECT c FROM Cab c WHERE c.isAvailable = :availability")
    List<CabDTO> findAvailableCabs(@Param("availability") boolean availability);
}