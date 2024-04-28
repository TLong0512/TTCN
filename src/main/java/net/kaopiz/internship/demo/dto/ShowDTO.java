package net.kaopiz.internship.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ShowDTO {

	private Date startedTime;

	private Date endedTime;

	private Boolean isFull = Boolean.FALSE;

	private String roomName;

	private String filmName;

}
