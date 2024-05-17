package com.team12.fantafilm.model.dto;

import lombok.Data;

@Data
public class SeatDTO {
    private Long id;
    private String name;
    private int isOccupied;

}
