package com.CabBookingSystem.CabService.Services.Impl;

import com.CabBookingSystem.CabService.DTO.CabDTO;
import com.CabBookingSystem.CabService.Entity.Cab;
import com.CabBookingSystem.CabService.Repo.CabRepository;
import com.CabBookingSystem.CabService.Services.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabserviceImpl implements CabService {
    @Autowired
    private CabRepository cabRepository;

    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public CabDTO register(CabDTO cabDTO) {
        Cab cab = new Cab();
        cab.setLicensePlate(cabDTO.getLicensePlate());
        cab.setType(cabDTO.getType());
       cab.setAvailable(cabDTO.isAvailable());

       cabRepository.save(cab);
        kafkaTemplate.send("cab-registered", "cab registered: " + cab.getLicensePlate());
       return new CabDTO(cab);
    }
    @Override
    public Optional<CabDTO> updateCab(String cabId, CabDTO cabDTO) {
        Optional<Cab> optionalCab = cabRepository.findById(cabId);
        if (optionalCab.isPresent()) {
            Cab cab = optionalCab.get();
            cab.setLicensePlate(cabDTO.getLicensePlate());
            cab.setType(cabDTO.getType());
            cab.setAvailable(cabDTO.isAvailable());
            cabRepository.save(cab);
            kafkaTemplate.send("cab-updated", "cab updated: " + cab.getLicensePlate());

            return Optional.of(new CabDTO(cab));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<CabDTO> getAvailableCabs() {
            return cabRepository.findAvailableCabs(true);
    }

    @Override
    public void removeCab(String cabId) {
        cabRepository.deleteById(cabId);
        kafkaTemplate.send("cab-deleted", "cab deleted: " + cabId);

    }
}
