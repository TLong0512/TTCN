package net.kaopiz.internship.demo.dto;

import lombok.Data;

@Data
public class OrderDTO {

	private int numberOfCombos;

	private String status;

	private String paymentMethod;

	private UserDTO user;

}
