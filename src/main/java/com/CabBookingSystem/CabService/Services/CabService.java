package com.CabBookingSystem.CabService.Services;

import com.CabBookingSystem.CabService.DTO.CabDTO;

import java.util.List;
import java.util.Optional;

public interface CabService {
    public CabDTO register(CabDTO cabDTO);
    public Optional<CabDTO> updateCab(String cabId, CabDTO updatedCab);
    public List<CabDTO> getAvailableCabs();
    public void removeCab(String cabId);

}
