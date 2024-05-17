package com.team12.fantafilm.model.dto;


import com.team12.fantafilm.model.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DtoSeat {
    private Long id;
    private Room room;
    private String code;
    private String line;
    private String description;
    private Integer status;
    private Integer number;
    private String isOccupied;
    private String ticketId;
    private SeatType seatType;

}
