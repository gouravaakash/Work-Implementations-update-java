package com.CabBookingSystem.CabService.Controller;

import com.CabBookingSystem.CabService.DTO.CabDTO;
import com.CabBookingSystem.CabService.Services.Impl.CabserviceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CabBookingSystem.CabService.Services.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cabs")
public class CabController {

    @Autowired
    private CabserviceImpl cabService;

    @PostMapping("/register")
    public ResponseEntity<CabDTO> registerCab(@RequestBody CabDTO cabDTO) {
        CabDTO registeredCab = cabService.register(cabDTO);
        return ResponseEntity.ok(registeredCab);
    }

    @PutMapping("/update/{cabId}")
    public ResponseEntity<CabDTO> updateCab(@PathVariable String cabId, @RequestBody CabDTO cabDTO) {
        Optional<CabDTO> updatedCab = cabService.updateCab(cabId, cabDTO);
        return updatedCab.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    public ResponseEntity<List<CabDTO>> getAvailableCabs() {
        List<CabDTO> availableCabs = cabService.getAvailableCabs();
        return ResponseEntity.ok(availableCabs);
    }

    @DeleteMapping("/remove/{cabId}")
    public ResponseEntity<Void> removeCab(@PathVariable String cabId) {
        cabService.removeCab(cabId);
        return ResponseEntity.noContent().build();
    }
}
