package net.kaopiz.internship.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cinema {

	private String location;

	private String name;

	private int totalRoom;

	private List<CinemaRoomDTO> cinemaRooms = new ArrayList<>();

}
