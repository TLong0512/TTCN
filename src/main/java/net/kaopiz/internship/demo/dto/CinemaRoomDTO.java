package net.kaopiz.internship.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CinemaRoomDTO {

	private String description;

	private String name;

	private String phoneNumber;

	private Long cinemaId;

	private List<RoomSeatDTO> roomSeats = new ArrayList<>();

}
