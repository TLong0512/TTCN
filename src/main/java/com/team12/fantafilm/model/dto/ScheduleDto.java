package com.team12.fantafilm.model.dto;


import com.team12.fantafilm.model.Cinema;
import com.team12.fantafilm.model.Movie;
import com.team12.fantafilm.model.Room;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ScheduleDto {
    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime finishAt;

//    private LocalTime startTime;
    private Cinema cinema;
    private Room room;
    private Movie movie;
}
