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
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cinema_rooms")
public class CinemaRoom extends AbstractBase {

	@Column(name = "name", unique = true)
	@NotBlank(message = "Room's name can't be blank")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Room_Seat> roomSeats;

	@JsonIgnore
	@OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToStringExclude
	private Collection<Show> shows;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;

	@Column(name = "cinema_room_code")
	private String code;

	@Column(name = "cinema_room_status")
	private String status;

}
