package net.kaopiz.internship.demo.dto;

import lombok.Data;

@Data
public class ShowSeatDTO {

	private Boolean isAvailable = Boolean.TRUE;

	private double price;

	private String roomSeat;

	private String showName;

}
