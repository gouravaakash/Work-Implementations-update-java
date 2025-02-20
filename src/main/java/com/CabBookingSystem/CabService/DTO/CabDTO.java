package com.CabBookingSystem.CabService.DTO;

import com.CabBookingSystem.CabService.Entity.Cab;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CabDTO {
    private String id ;
    private String licensePlate;
    private String type;
    private boolean isAvailable;

    public CabDTO(Cab cab) {
        this.id=cab.getId();
        this.licensePlate = cab.getLicensePlate();
        this.type = cab.getType();
        this.isAvailable =cab.isAvailable();
    }

}
