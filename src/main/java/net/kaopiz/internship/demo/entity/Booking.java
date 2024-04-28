package net.kaopiz.internship.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking extends AbstractBase {

	private static final long serialVersionUID = 1L;

	@Column(name = "bookings_status")
	private String status;

	@Column(name = "is_cancel")
	private boolean is_Cancel;

	@Column(name = "number_of_seats")
	private int number_of_seats;

	@ManyToOne
	@JoinColumn(name = "show_id")
	private Show show;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
