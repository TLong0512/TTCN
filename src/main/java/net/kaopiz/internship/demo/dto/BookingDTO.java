package net.kaopiz.internship.demo.dto;

import lombok.Data;

@Data
public class BookingDTO {

	private Boolean isCancel = Boolean.FALSE;

	private int numberOfSeat;

	private String status;

	private Long orderId;

	private Long showId;
}
