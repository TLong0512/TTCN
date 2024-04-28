package net.kaopiz.internship.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class ComboDTO {

	private String name;

	private double price;

	private String thumbnail;

	private List<OrderDetailComboDTO> orderDetailCombos;

}
