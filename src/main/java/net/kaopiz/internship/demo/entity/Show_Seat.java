package net.kaopiz.internship.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "show_seats")
public class Show_Seat extends AbstractBase {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Show_seats's price must not be blank")
	@Column(name = "price")
	private double price;

	@NotBlank(message = "Show_seats must be available or not")
	@Column(name = "is_available")
	private boolean isAvailable;

	@ManyToOne
	@JoinColumn(name = "room_seat_id")
	private Room_Seat roomSeat;

	@ManyToOne
	@JoinColumn(name = "show_id")
	private Show show;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

}
