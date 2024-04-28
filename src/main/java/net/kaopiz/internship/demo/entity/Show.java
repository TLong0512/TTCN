package net.kaopiz.internship.demo.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "shows")
public class Show extends AbstractBase {

	@ManyToOne
	@JoinColumn(name = "room_id")
	private CinemaRoom cinemaRoom;

	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;

	@Column(name = "start_time")
	private LocalDateTime startTime;

	@Column(name = "end_time")
	private LocalDateTime endTime;

	@Column(name = "is_full")
	private Boolean isFull;

	@Column(name = "code")
	private String code;

	@JsonIgnore
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToStringExclude
	private Collection<Show_Seat> show_Seats;
}
