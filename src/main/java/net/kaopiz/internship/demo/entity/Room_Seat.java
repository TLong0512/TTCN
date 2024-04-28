package net.kaopiz.internship.demo.entity;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_seats")
public class Room_Seat extends AbstractBase {

	@Column(name = "seat_type")
	private String type;

	@Column(name = "seat_position")
	private String position;

	@Column(name = "seat_row")
	private Character row;

	@Column(name = "seat_column")
	private Integer column;

	@Column(name = "seat_code")
	private String code;

	@Column(name = "seat_status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cinema_room_id")
	private CinemaRoom cinemaRoom;

	@JsonIgnore
	@OneToMany(mappedBy = "roomSeat", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToStringExclude
	private Collection<Show_Seat> showSeats;

}
